(ns tadam.templates-test
  (:require [clojure.test :refer :all]
            [tadam.templates :as templates]
            [clojure.java.io :refer [resource]]))

(deftest render-template
  (testing "Simple"
    (is (= (templates/render-template (resource "html/simple.html") {}) "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n")))
  (testing "With param"
    (is (= (templates/render-template (resource "html/with-param.html") {:rating "Awesome" :url "https://tadam-framework.dev"}) "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n"))))

(deftest render-HTML
  (testing "Simple"
    (is (= (templates/render-HTML {:req "test"} (resource "html/simple.html") {}) {:status 200, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil})))
  (testing "With param"
    (is (= (templates/render-HTML {:req "test"} (resource "html/with-param.html") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 200, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil}))))

(deftest render-404
  (testing "Simple"
    (is (= (templates/render-404 {:req "test"} (resource "html/simple.html") {}) {:status 404, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil})))
  (testing "With param"
    (is (= (templates/render-404 {:req "test"} (resource "html/with-param.html") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 404, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<html><body><h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p></body></html>\n", :session nil}))))

(deftest render-markdown
  (testing "Simple"
    (is (= (templates/render-markdown {:req "test"} (resource "markdown/simple.md") {}) {:status 200, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<h1>Title</h1><p>Hello <strong>world</strong></p>", :session nil})))
  (testing "With param"
    (is (= (templates/render-markdown {:req "test"} (resource "markdown/with-param.md") {:rating "Awesome" :url "https://tadam-framework.dev"}) {:status 200, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "text/html"}, :body "<h1>Title</h1><p>Tadam is Awesome</p><p><a href='https://tadam-framework.dev'>Website</a></p>", :session nil}))))

(deftest render-json
  (testing "Simple"
    (is (= (templates/render-JSON {:req "test"} {:name "Tadam" :type "Framework"}) {:status 200, :headers {"X-Powered-By" "Clojure/Tadam", "Content-Type" "application/json"}, :body "{\"name\":\"Tadam\",\"type\":\"Framework\"}", :session nil}))))
