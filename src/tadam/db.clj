(ns tadam.db
  (:require
   [environ.core :refer [env]]
   [migratus.core :as migratus]
   [clojure.java.jdbc :as j]
   [buddy.hashers :as hashers]
   ))

(defn cast-data-db
  "Cast data from project.clj and sets SQLite path to point to the resources folder"
  [raw-data]
  (if-not (nil? raw-data)
    (let [cast-data (clojure.edn/read-string raw-data)]
      (assoc cast-data :subname (.getFile (clojure.java.io/resource (:subname cast-data)))))))

(def db (cast-data-db (env :db)))

;;;; QUERIES

(defn query_all
  "Query in database with multiple results"
  [sql]
  (j/query db sql))

(defn query_one
  "Query in database with one result"
  [sql]
  (into {} (query_all sql)))

(defn insert
  "Insert in database"
  [table data]
  (j/insert-multi! db table data))

(defn update
  "Update in database"
  [table data where]
  (j/update! db table data where))

(defn delete
  "Delete in database"
  [table data where]
  (j/delete! db table data where))

;;;; END QUERIES

;;;; MIGRATIONS

(def config-migrations {:store                :database
                        :migration-dir        "migrations/"
                        :init-script          "init.sql"
                        :init-in-transaction? false
                        :db                   db})
(defn migrate
  "Migrate"
  []
  (migratus/migrate config-migrations))

(defn rollback
  "Rollback"
  []
  (migratus/rollback config-migrations))

;;;; END MIGRATIONS

;;;; SUPERUSERS
(defmacro prn-console
  "Prints text by console without double quotes with new line."
  [nsym] `(println "" ~(name nsym) ""))

(defn createsuperuser
  "Create superuser"
  []
  ;; Get first name
  (prn-console "> First name:")
  (let [first-name (read-line)]
    ;; Get email
    (prn-console "> E-mail: ")
    (let [email (read-line)]
      ;; Get password
      (prn-console "> Password: ")
      (let [password (read-line)]
        ;; Create superuser
        (insert :users [{:first_name first-name :email email :password (hashers/derive password) :role_id 1 :is_active true}])
        ;; Informs the user
        (prn-console "🎩Superuser created with great success 🥳!")))))

(defn listsuperusers
  "List all superusers"
  []
  (let [all-superusers (query_all ["SELECT first_name, email FROM users"])]
    (doall (map #(prn (str (:first_name %) " - " (:email %))) all-superusers))))
;;;; END SUPERUSERS
