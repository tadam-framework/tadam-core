(ns tadam.responses
  (:require
   [ring.util.response :as ring-response]))

(defn response
  "Return response"
  ([req body]
   {:status  200
    :headers {"Content-Type" "text/html"}
    :body    body
    :session (-> req :session)})
  ([req body status]
   {:status  status
    :headers {"Content-Type" "text/html"}
    :body    body
    :session (-> req :session)})
  ([req body status content-type]
   {:status  status
    :headers {"Content-Type" content-type}
    :body    body
    :session (-> req :session)}))

(defn redirect
  "303 See Other"
  ([req url]
   (-> (ring-response/redirect url)
       (assoc :session (:session req))
       (assoc :status 303)))
  ([req url status]
   (-> (ring-response/redirect url)
       (assoc :session (:session req))
       (assoc :status status))))

(defn redirect-permanent
  "308 Permanent Redirect"
  [req url]
  (redirect req url 308))
