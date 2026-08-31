(ns clojure-noob.core
  (:gen-class)
  (:require
   [clojure.string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "padulkemid"))

(println "you guess it")

(defn train [] (println "tuuuuut"))

(+ 1 2 3 4)

(if false (do (println "true") "ole true")
    (do (println "false") "ole false"))

(when true (println "success") "yeah")

(or false nil :large_venti :why_aslkdjalskd)
(or (= 0 1) (= "yes" "no") (= 0 0))

(def loco 1)
(def loco_2 1)
(= loco loco_2)

(def person {:first-name "smarty" :last-name "mcsmartpants"})
(get person :first-name)

(def a '(0 1 2 3 4))
(nth a 2)
(conj a 20)
(nth a 0)
(map inc a)

(defn loki
  "this is a loki func"
  [check]
  (str "dammit " check " you loki?"))

(loki (or false "row"))

(defn lakai-first
  [{:keys [a]}]
  (str "yeah" a))

(lakai-first {:a "stop"})
(#(* % 3) 8)

(defn body-parts
  "it will define body parts map for our hobbit"
  [name size]
  {:name name :size size})

(def asym-hobbit-body-parts [(body-parts "head" 3)
                             (body-parts "left-eye" 1)
                             (body-parts "left-ear" 1)
                             (body-parts "mouth" 1)
                             (body-parts "nose" 1)
                             (body-parts "neck" 2)
                             (body-parts "left-shoulder" 3)
                             (body-parts "left-upper-arm" 3)
                             (body-parts "chest" 10)
                             (body-parts "back" 10)
                             (body-parts "left-forearm" 3)
                             (body-parts "abdomen" 6)
                             (body-parts "left-kidney" 1)
                             (body-parts "left-hand" 2)
                             (body-parts "left-knee" 2)
                             (body-parts "left-thigh" 4)
                             (body-parts "left-lower-leg" 3)
                             (body-parts "left-achilles" 1)
                             (body-parts "left-foot" 2)])

(defn matching-part
  "create the matching part from left to right"
  [part]
  (body-parts
   (clojure.string/replace (:name part) #"^left-" "right-")
   (:size part)))

(defn symmetrize-body-parts
  "expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (println "remains >> " remaining-asym-parts " and asym body >> " asym-body-parts)
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(let [x 3
      y (* x 3)
      z (+ x 4)
      [raw & a] [y 1 2 3]]
  [z a raw])

(loop [stupedo 0
       lacoste []]
  (if (= stupedo 10)
    lacoste
    (recur (inc stupedo)
           (conj lacoste stupedo))))

(defn better-symmetrize-body-parts
  "using reduce instead of loop"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts
                  (set [part (matching-part part)])))
          []
          asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn two-sum
  "2 sum in clojure from oop perspective"
  [nums target]
  (loop [idx 0
         seen {}]
    (println "you check >> " idx " asd " seen)
    (if (< idx (count nums))
      (let [num (nth nums idx)
            complement (- target num)]
        (if (contains? seen complement)
          [(seen complement) idx]
          (recur (inc idx)
                 (assoc seen num idx))))
      nil)))

(two-sum [2 11 7 15] 9)






