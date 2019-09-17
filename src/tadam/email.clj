(ns tadam.email
  (:require
   [environ.core :refer [env]]
   [postal.core :refer [send-message]]
   ))

(defn send
  "Add session in request"
  [to subject content-html content-plain]
  (send-message {:host (env :email-host)
                 :user (env :email-user)
                 :pass (env :email-pass)
                 :ssl  (env :email-ssl)
                 :port (env :email-port)}
                {:from    (env :email-from)
                 :to      to
                 :subject subject
                 :type    "text/html; charset=utf-8"
                 :body    [:alternative
                           {:type    "text/plain;charset=utf-8"
                            :content (env content-plain)}
                           {:type    "text/html;charset=utf-8"
                            :content content-html}]}))
