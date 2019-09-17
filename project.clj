(defproject tadam "0.2.0"
  :description "First version"
  :url "https://github.com/tadam-framework/tadam"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 ;; Clojure
                 [org.clojure/clojure "1.10.0"]
                 ;; Managing environment settings
                 [environ "1.1.0"]
                 ;; Templates
                 [selmer "1.12.12"]
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
                 [com.draines/postal "2.0.3"]
                 ]
  ;; ALIAS
  :repl-options {:init-ns tadam.core})
