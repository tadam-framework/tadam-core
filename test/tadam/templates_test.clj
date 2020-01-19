(ns tadam.templates-test
  (:require [clojure.test :refer :all]
            [tadam.templates :as templates]))

(deftest raw-HTML
  (testing "Raw HTML simple"
    (is (= (templates/raw-HTML "<html><body></body></html>" {}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body></body></html>"})))
  (testing "Raw HTML with param"
    (is (= (templates/raw-HTML "<html><body>{{ hello }}</body></html>" {:hello "world"}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body>world</body></html>"}))))
