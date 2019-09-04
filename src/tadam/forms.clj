(ns noticias-web.tadam.forms
  (:require
   [jkkramer.verily :as v]
   [tadam.utils :refer [lazy-contains?]]
   ))

(defn validate
  ;; From some parameters and rules, return a hashmap with the validations
  [params rules]
  ;; Get all errors from verily
  (def my-validations (v/validate (clojure.walk/keywordize-keys params) rules))
  ;; Get keys with errors
  (def my-keys (distinct (flatten (map #(:keys %) my-validations))))
  ;; Return hashmap
  {
   :errors (into {} (map (fn [key] (assoc {} key (vec (map (fn [validation-filter] (:msg validation-filter)) (filter (fn [validation] (lazy-contains? (:keys validation) key)) my-validations))))) my-keys))
   })


