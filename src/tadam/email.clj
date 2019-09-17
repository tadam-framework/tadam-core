(ns tadam.email
  (:require
   [environ.core :refer [env]]
   [postal.core :refer [send-message]]
   ))

(defn send
  "Add session in request"
  [to subject content-html content-plain]
  (let [env-email (clojure.edn/read-string (:email env))]
    (send-message {:host (:host env-email)
                   :user (:user env-email)
                   :pass (:pass env-email)
                   :ssl  (:ssl env-email)
                   :port (:port env-email)}
                  {:from    (:from env-email)
                   :to      to
                   :subject subject
                   :type    "text/html; charset=utf-8"
                   :body    [:alternative
                             {:type    "text/plain;charset=utf-8"
                              :content content-plain}
                             {:type    "text/html;charset=utf-8"
                              :content content-html}]})))
