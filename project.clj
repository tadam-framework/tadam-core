(defproject tadam-core "0.4.3"
  :description "Tadam Framework Core: Library to create dynamic pages"
  :url "https://github.com/tadam-framework/tadam-core"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [;; Clojure
                 [org.clojure/clojure "1.10.1"]
                 ;; HTTP Server
                 [ring "1.7.1"]
                 ;; Templates
                 [selmer "1.12.12"]
                 [markdown-clj "1.10.1"]
                 ;; JSON encoding
                 [cheshire "5.9.0"]
                 ;; Validations
                 [jkkramer/verily "0.6.0"]
                 ;; Migrations
                 [migratus "1.2.4"]
                 ;; Module is dedicated to cryptographic API
                 [buddy/buddy-core "1.6.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 ;; Send emails
                 [clj-kondo "RELEASE"]
                 [com.draines/postal "2.0.3"]]
  :plugins [;; DEV TOOLS
            ;;; Check idiomatic bug
            [lein-kibit "0.1.7"]]
  ;; ALIAS
  :aliases {"check-idiomatic" ["kibit" "src"]
            "check-lint"       ["run" "-m" "clj-kondo.main" "--lint" "src"]}
  :repl-options {:init-ns tadam.core})
