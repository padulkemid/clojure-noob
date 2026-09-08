(ns clojure-noob.core
  (:gen-class)
  (:require
   [clojure.string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "padulkemid"))

(println "you guess it")

(defn tralo [] (println "tuuuuut"))

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
    (if (< idx (count nums))
      (let [num (nth nums idx)
            complement (- target num)]
        (if (contains? seen complement)
          [(seen complement) idx]
          (recur (inc idx)
                 (assoc seen num idx))))
      nil)))

(two-sum [2 11 7 15] 9)

;; continue
(defn hit
  "this will hit the body parts"
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & rest] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (let [head (first rest)]
          (recur rest
                 (+ accumulated-size (:size head))))))))

(hit asym-hobbit-body-parts)
(map inc [1 2 3 4])

;; exercise 1
;; write a function that takes a number and adds 100 to it
(defn inc100
  "will inc + 100"
  [num]
  (+ 100 num))

(inc100 100)

;; write a function, [dec-maker] that works
;; exactly like the function [inc-maker]
;; except with subtractionz
(defn dec-maker
  "decrease func"
  [num]
  #(- % num))

;; above is the shorthand for this
(defn dec-maker-verbose
  "verbose decrease func"
  [num]
  (fn [decreaser]
    (- decreaser num)))

(def dec9 (dec-maker 9))
(def dec10 (dec-maker-verbose 10))
(dec9 100)
(dec10 100)

;; write a function [mapset] that works like a [map]
;; except the return value is a set
(defn mapset
  "return a set from a vector"
  [cb vec]
  (into (sorted-set) (map cb vec)))

(mapset inc [1 1 2 2])

;; write a function that is similart to [symmetrize-body-parts]
;; except that it has to work with radial symmetry. [5 body parts]
;; [REVISIT]

;; write a function that generalizes [symmetrize-body-parts]
;; et (function you made above) and it should accept collection
;; and number of the parts of matchibg body (so not just 5 or 2)
;; but can be anything.
;; [REVISIT]

;;; Chapter 4
;; colls
(seq '(1 2 3))
(seq [1 2 3])
(seq #{1 2 3})
(seq {:first-name "sukkari" :last-name "maria"})

;; so far I just know how to operate [map] with this
(map inc '(1 2 3))

;; you can actually do this
(into #{} (map str ["a" "b" "c"] ["A" "B" "C"]))

(def human-consumtpion [8.1 7.2 2 7])
(def critter-consumtpion [0.0 0.3 5 1.2])
(defn unify-diet-data
  "this is the merged diet data of human and critter"
  [human critter]
  {:human human :critter critter})

(map unify-diet-data human-consumtpion critter-consumtpion)
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  "it will map the numbers into how much is the sum, count,
  and average"
  [numbers]
  (let [[sum count avg] (map  #(% numbers) [sum count avg])]
    {:sum sum :count count :avg avg}))

(stats [3 4 10])

;; using map to get values from key
(def identities
  [{:alias "batman" :real "bruce wayne"}
   {:alias "sena" :real "alize"}
   {:alias "baba" :real "baba"}])

(map :alias identities)

(defn cheque
  "implementing this in reduce"
  [nm om]
  (let [[k v] om]
    (assoc nm k (inc v))))

(reduce cheque {} {:max 30 :min 10})

(take 3 [1 2 3 4 5])
(drop 3 [1 2 3 4 5])

(defn check-under
  "check the usage of `<`"
  [m]
  (< (:month m) 3))

(check-under {:month 1 :day 1})

(def journal [{:month 1 :day 1 :human 5.3 :critter 2.3}
              {:month 1 :day 2 :human 5.1 :critter 2.0}
              {:month 2 :day 1 :human 4.0 :critter 1.3}
              {:month 3 :day 2 :human 2.3 :critter 4.3}])

(take-while check-under journal)
(drop-while check-under journal)
