(ns clojure-noob.check)

;; into
(def scourge (map identity {:sunlight-reaction "Glitter!"}))
;; this will be a `lazy-seq`
scourge
;; lets make this into a map again
(into {} scourge)

;; build binary search based on clojure
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
