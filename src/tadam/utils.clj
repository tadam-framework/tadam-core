(ns tadam.utils)

(defn is-post
  "Check if request is POST"
  [req]
  (= (req :request-method) :post))

(defn lazy-contains?
  "Check if contains lazy ignore"
  [col key]
  (some #{key} col))
