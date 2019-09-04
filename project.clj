(defproject tadam "0.1.0"
  :description "First version"
  :url "https://github.com/tadam-framework/tadam"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  ;; ALIAS
  :aliases {
            "migrate"  ["run" "-m" "tadam.db/migrate"]
            "rollback" ["run" "-m" "tadam.db/rollback"]
            }
  :repl-options {:init-ns tadam.core})
