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
  "307 Temporary Redirect"
  ([req url]
   (-> (response/redirect url)
       (assoc :session (-> req :session))
       (assoc :status 307)))
  ([req url status]
   (-> (response/redirect url)
       (assoc :session (-> req :session))
       (assoc :status status))))

(defn redirect-GET
  "303 Temporary Redirect"
  [req url]
  (redirect req url 303))

(defn redirect-permanent
  "308 Permanent Redirect"
  [req url]
  (redirect req url 303))
