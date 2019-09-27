(ns tadam.utils
  (:require
   [ring.util.response :as response]))

(defn get-params
  "Get form parameters"
  [req]
  (when (contains? req :params)
    (clojure.walk/keywordize-keys (:params req))))

(defn is-post
  "Check if request is POST"
  [req]
  (= (req :request-method) :post))

(defn lazy-contains?
  "Check if contains lazy ignore"
  [col key]
  (some #{key} col))

(defn redirect
  "303 See Other"
  ([req url]
   (-> (response/redirect url)
       (assoc :session (:session req))
       (assoc :status 303)))
  ([req url status]
   (-> (response/redirect url)
       (assoc :session (:session req))
       (assoc :status status))))

(defn redirect-permanent
  "308 Permanent Redirect"
  [req url]
  (redirect req url 308))
