(ns tadam.templates
  (:require
   [environ.core :refer [env]]
   [tadam.responses :refer [response]]
   [clojure.java.io :refer [resource]]
   [selmer.parser :as s]
   [markdown.core :refer [md-to-html-string]]
   [cheshire.core :refer [generate-string]]))

;; Disabled cache in debug
(if (:debug env)
  (s/cache-off!) ())

;; Path templates
(s/set-resource-path! (resource "templates"))

(defn raw-HTML
  "Render raw HTML"
  [req text params]
  (response req (s/render text params)))

(defn render-HTML
  "Render to HTML"
  ([req template params]
   (response req (s/render-file template params))))

(defn render-markdown
  "Render markdown to HTML"
  [req template params]
  (response req (md-to-html-string (s/render (slurp template) params))))

(defn render-JSON
  "Render JSON"
  [req hash-map]
  (response req (generate-string hash-map) "application/json"))
