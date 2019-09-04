(ns noticias-web.tadam.templates
  (:require
   [environ.core :refer [env]]
   [clojure.java.io :refer [resource]]
   [selmer.parser :as s]
   [cheshire.core :refer [generate-string]]
   ))

;; Disabled cache in debug
(if (:debug env)
  (s/cache-off!))

;; Path templates
(s/set-resource-path! (resource "templates") )

(defn raw-HTML
  ;; Render raw HTML
  [text params]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (s/render text params)})

(defn render-HTML
  ;; Render to HTML
  [template params]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (s/render-file template params)})

(defn render-JSON
  ;; Render JSON
  [hash-map]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (generate-string hash-map)})

(defn render-404
  ;; Render 404 HTML
  []
  {:status  404
   :headers {"Content-Type" "text/html"}
   :body    (s/render-file "404.html" {})})
