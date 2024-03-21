(ns gaussmatrix.matrix 
  (:require [clojure.core.matrix :as cmx]))

(cmx/set-current-implementation :vectorz)

(defn gen-matrix 
  "Generates a matrix" 
  [coll]
  (cmx/matrix coll))

(defn add-zeros-col 
  "Add a column full of zeros" 
  [matrix]
  (cmx/transpose 
   (cmx/conjoin 
    (cmx/transpose matrix) 
    (cmx/zero-vector (cmx/row-count matrix))
    )))

(defn square-matrix?
  "Checks if the matrix has the same height and width"
  [matrix]
  (reduce = (cmx/shape matrix)))

(defn invertible? 
  "Checks if the matrix could be inverted" 
  [matrix]
  (not= (cmx/det matrix) 0))

(defn solve 
  "Solve the matrix using Gaussian reduction"
  [matrix]
  (let [is-square (square-matrix? matrix)
        is-invertible (invertible? matrix)
        augmented-matrix (add-zeros-col matrix)]
    (when-not is-square (throw (RuntimeException. "Matrix must be square")))
    (when-not is-invertible (throw (RuntimeException. "Matrix must be invertible")))
    augmented-matrix
    ))

