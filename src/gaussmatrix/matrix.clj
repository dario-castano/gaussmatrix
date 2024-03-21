(ns gaussmatrix.matrix 
  (:require [clojure.core.matrix :as cmx]))

(cmx/set-current-implementation :vectorz)

(defn gen-matrix 
  "Generates a matrix" 
  [coll]
  (cmx/matrix coll))

(defn correct-shape?
  "Checks if the matrix has the same height and width"
  [matrix]
  (= (cmx/row-count matrix) (dec (cmx/column-count matrix))))

(defn invertible? 
  "Checks if the matrix could be inverted" 
  [matrix]
  (not= (cmx/det (cmx/reshape matrix (repeat 2 (cmx/row-count matrix)))) 0))

(defn solve 
  "Solve the matrix using Gaussian reduction"
  [matrix]
  (let [right-shape (correct-shape? matrix)
        is-invertible (invertible? matrix)
        cols (range (cmx/row-count matrix))
        rows (range (cmx/row-count matrix))
        ]
    (when-not right-shape (throw (RuntimeException. "Matrix must be square")))
    (when-not is-invertible (throw (RuntimeException. "Matrix must be invertible")))
    ))

