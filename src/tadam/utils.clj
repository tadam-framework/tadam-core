(ns tadam.utils
  (:require
   [cheshire.core :refer [parse-string]]
   ))

(defn is-post
  "Check if request is POST"
  [req]
  (= (req :request-method) :post))

(defn get-JSON
  "Get JSON from request"
  [req]
  (parse-string (slurp (-> req :body)) true))

(defn lazy-contains?
  "Check if contains lazy ignore"
  [col key]
  (some #{key} col))
