(ns gaussmatrix.matrix 
  (:require [clojure.core.matrix :as cmx]))

(defn gen-matrix 
  "Generates a matrix" 
  [coll]
  (cmx/matrix coll))

(defn square-matrix?
  "Checks if the matrix has the same height and width"
  [matrix]
  (= (cmx/column-count matrix) (cmx/row-count matrix)))

(defn solve 
  "Solve the matrix using Gaussian reduction"
  [matrix]
  (cmx/matrix matrix))

