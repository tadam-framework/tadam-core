(ns tadam.utils
  (:require
   [ring.util.response :as response]))

(defn get-params
  "Get form parameters"
  [req]
  (if (contains? req :params)
    (clojure.walk/keywordize-keys (:params req))
    nil))

(defn is-post
  "Check if request is POST"
  [req]
  (= (req :request-method) :post))

(defn lazy-contains?
  "Check if contains lazy ignore"
  [col key]
  (some #{key} col))

(defn redirect
  "Redirect request"
  [req url]
  (-> (response/redirect url)
      (assoc :session (-> req :session))
      (assoc :status 301)))
