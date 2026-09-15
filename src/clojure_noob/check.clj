(ns clojure-noob.check)

;; into
(def scourge (map identity {:sunlight-reaction "Glitter!"}))
;; this will be a `lazy-seq`
scourge
;; lets make this into a map again
(into {} scourge)

;; build binary search based on clojure
;; binary search requires MONOTONIC condition
;; which means it should increase in one way
(defn binary-search
  "return the index in O(log n)"
  [v t]
  (loop [left 0
         right (dec (count v))]
    (if (<= left right)
      (let [x (+ left right)
            m (quot x 2)
            v-m (get v m)]
        (cond
          (= v-m t) m
          (< v-m t) (recur (inc m) right)
          :else (recur left (dec m))))
      nil)))

(binary-search (vec (range 230 16782)) 782)

;; how about checking something like about
;; the first true?
(defn binary-search-bool
  "binary search but filter the first true"
  [v]
  (loop [left 0
         right (dec (count v))
         b nil]
    (if (<= left right)
      (let [s (+ left right)
            m (quot s 2)]
        (if (= (get v m) true)
          (recur left (dec m) m)
          (recur (inc m) right b)))
      b)))

(binary-search-bool [false false false true true])
(take 2 (range 0 10))
