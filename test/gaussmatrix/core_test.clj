(ns gaussmatrix.core-test
  (:require [clojure.test :refer :all]
            [gaussmatrix.core :refer :all]
            [gaussmatrix.matrix :as mx]))

(deftest main-solves-ok
  (testing "The application reduces the matrix successfsully"
    (let  [m (mapcat conj (gaussmatrix.core/-main))
           expected (mapcat conj 
                            (mx/gen-matrix [[1.0 0.0 0.0 0.0 -0.5]
                                            [0.0 1.0 0.0 0.0 2.625]
                                            [0.0 0.0 1.0 0.0 3.625]
                                            [0.0 0.0 0.0 1.0 1.125]]))]
      
      (is (every? true? (map (fn [x y] (zero? (- x y))) m expected))))))
