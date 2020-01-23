(ns tadam.templates-test
  (:require [clojure.test :refer :all]
            [tadam.templates :as templates]
            [clojure.java.io :refer [resource]]))

(deftest raw-HTML
  (testing "Raw HTML simple"
    (is (= (templates/raw-HTML {:req "test"} "<html><body></body></html>" {}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body></body></html>", :session nil})))
  (testing "Raw HTML with param"
    (is (= (templates/raw-HTML {:req "test"} "<html><body>{{ hello }}</body></html>" {:hello "world"}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body>world</body></html>", :session nil}))))

(deftest render-HTML
  (testing "Simple"
    (is (= (templates/render-HTML {:req "test"} (resource "html/simple.html") {}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil})))
  (testing "With param"
    (is (= (templates/render-HTML {:req "test"} (resource "html/with-param.html") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil}))))

(deftest render-404
  (testing "Simple"
    (is (= (templates/render-404 {:req "test"} (resource "html/simple.html") {}) {:status 404, :headers {"Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil})))
  (testing "With param"
    (is (= (templates/render-404 {:req "test"} (resource "html/with-param.html") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 404, :headers {"Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil}))))

(deftest render-markdown
  (testing "Simple"
    (is (= (templates/render-markdown {:req "test"} (resource "markdown/simple.md") {}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<h1>Title</h1><p>Hello <strong>world</strong></p>", :session nil})))
  (testing "With param"
    (is (= (templates/render-markdown {:req "test"} (resource "markdown/with-param.md") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p>", :session nil}))))

(deftest render-json
  (testing "Simple"
    (is (= (templates/render-JSON {:req "test"} {:name "Tadam" :type "Framework"}) {:status 200, :headers {"Content-Type" "application/json"}, :body "{\"name\":\"Tadam\",\"type\":\"Framework\"}", :session nil}))))
