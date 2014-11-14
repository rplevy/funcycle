(ns red.ucycle-test
  (:require [clojure.test :refer :all]
            [red.ucycle :refer :all]))

(deftest rotate-tests
  (is (= [/ + - *] (crotate [+ - * /])))
  (is (= [- * / +] (rotate [+ - * /])))
  (is (= [+ - * /] (rotate (crotate [+ - * /])))))

(deftest reducycle-tests
  (is (= 5 (reducycle [+ -] 0 (range 1 10))))
  (is (= -5 (reducycle [+ -] 0 (range 1 11))))
  (is (= 6 (reducycle [+ -] 0 (range 1 12))))
  (is (= -6 (reducycle [+ -] 0 (range 1 13)))))
