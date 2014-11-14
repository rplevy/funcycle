(ns fun.cycle-test
  (:require [clojure.test :refer :all]
            [fun.cycle :refer :all]))

(deftest rotate-tests
  (is (= [/ + - *] (crotate [+ - * /])))
  (is (= [- * / +] (rotate [+ - * /])))
  (is (= [+ - * /] (rotate (crotate [+ - * /])))))

(deftest reducycle-tests
  (is (= 13 (reducycle [+ *] (range 1 5))))
  (is (= 20 (reducycle [* +] (range 1 5))))
  (is (= 5 (reducycle [+ -] 0 (range 1 10))))
  (is (= -5 (reducycle [+ -] 0 (range 1 11))))
  (is (= 6 (reducycle [+ -] 0 (range 1 12))))
  (is (= -6 (reducycle [+ -] 0 (range 1 13)))))

(deftest mapcycle-tests
  (is (= ["1" 2 "3" 4] (mapcycle [str int] (range 1 5))))
  (is (= ["a1" "b2" "a3" "b4"] (mapcycle [(partial str "a")
                                          (partial str "b")]
                                         (range 1 5))))
  (is (= ["a12" "b23" "a34" "b45"] (mapcycle [(partial str "a")
                                              (partial str "b")]
                                             (range 1 5)
                                             (range 2 6)))))
