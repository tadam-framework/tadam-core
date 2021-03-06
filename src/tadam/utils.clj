(ns tadam.utils
  (:require
   [cheshire.core :refer [parse-string]]))

(defn is-post
  "Check if request is POST"
  [req]
  (= (req :request-method) :post))

(defn get-JSON
  "Get JSON from request"
  [req]
  (parse-string (slurp (:body req)) true))

(defn get-header
  "Get header value from request"
  [req key]
  (-> req :headers (get key)))

(defn lazy-contains?
  "Check if contains lazy ignore"
  [col key]
  (some #{key} col))
