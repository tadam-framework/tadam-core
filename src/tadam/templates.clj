(ns tadam.templates
  (:require
   [tadam.responses :refer [response]]
   [clojure.java.io :refer [resource]]
   [selmer.parser :as s]
   [markdown.core :refer [md-to-html-string]]
   [cheshire.core :refer [generate-string]]))

;; Cache off
(s/cache-off!)
;; Path templates
(s/set-resource-path! (resource "templates"))

(defn render-template
  "Render template with params"
  [template params]
  (s/render-file template params))

(defn render-HTML
  "Render to HTML"
  ([req template params]
   (response req (s/render-file template params))))

(defn render-404
  "Render 404 HTML"
  ([req template params]
   (response req (s/render-file template params) 404)))

(defn render-markdown
  "Render markdown to HTML"
  [req template params]
  (response req (md-to-html-string (s/render (slurp template) params))))

(defn render-JSON
  "Render JSON"
  ([req hash-map]
   (response req (generate-string hash-map) 200 "application/json"))
  ([req hash-map status]
   (response req (generate-string hash-map) status "application/json")))
