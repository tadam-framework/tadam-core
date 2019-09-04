(ns tadam.utils)

(defn get-params [req]
  ;; Get params request
  (if (contains? req :params)
    (clojure.walk/keywordize-keys (:params req)))
  ({}))

(defn is-post [req]
  ;; Check is request POST
  (= (req :request-method) :post))

(defn lazy-contains? [col key]
  (some #{key} col))
