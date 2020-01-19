(ns tadam.templates-test
  (:require [clojure.test :refer :all]
            [tadam.templates :as templates]))

(deftest raw-HTML
  (testing "Raw HTML simple"
    (is (= (templates/raw-HTML "<html><body></body></html>" {}) {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body></body></html>"}))))
