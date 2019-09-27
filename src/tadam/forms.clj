(ns tadam.forms
  (:require
   [jkkramer.verily :as v]
   [tadam.utils :refer [lazy-contains?]]))

(defn validate
  "From some parameters and rules, return a hashmap with the validations"
  [params rules]
  (let [;; Get all errors from verily
        my-validations (v/validate (clojure.walk/keywordize-keys params) rules)
        ;; Get keys with errors
        my-keys        (distinct (flatten (map :keys my-validations)))]
    ;; Return hashmap
    {:errors (into {} (map (fn [key] (assoc {} key (vec (map (fn [validation-filter] (:msg validation-filter)) (filter (fn [validation] (lazy-contains? (:keys validation) key)) my-validations))))) my-keys))}))
