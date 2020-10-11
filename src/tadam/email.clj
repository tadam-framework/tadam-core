(ns tadam.email
  (:require
   [postal.core :refer [send-message]]))

(defn send-email
  "Send email"
  [config to subject content-html content-plain]
  (send-message {:host (:smtp-host config)
                 :user (:smtp-user config)
                 :pass (:smtp-password config)
                 :tls  (:smtp-tls config)
                 :port (:smtp-port config)}
                {:from    (:smtp-from config)
                 :to      to
                 :subject subject
                 :type    "text/html; charset=utf-8"
                 :body    [:alternative
                           {:type    "text/plain;charset=utf-8"
                            :content content-plain}
                           {:type    "text/html;charset=utf-8"
                            :content content-html}]}))
