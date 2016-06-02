(ns dlink-camera-api.core
  (:require [clojure.string :as string]
            [clj-http.client :as client]
            [clojure.data.codec.base64 :as b64])
  (:gen-class))

(defn- jmd5
  [md5str key]
  (string/join "" (map-indexed
   (fn [idx itm]
     (if (= key (mod idx 4))
       itm))
   md5str)))

(defn- cal-token
  [token1 token2 token3 token4]
  (let [real-tokens
        [(jmd5 token1 0)
         (jmd5 token2 1)
         (jmd5 token3 2)
         (jmd5 token4 3)]]
    (string/join ""
                 (map (fn [i]
                        (nth
                         (nth real-tokens (mod i 4))
                         (int (/ i 4))))
                      (range 0 32)))))

(defn- decode-b64-token
  [token]
  (apply
   str
   (map char
        (b64/decode (.getBytes (second token))))))

(defn do-camera-request
  [ip username password form-params]
  (let [dlink-magic-rand (int (+ 1 (rand 200)))
        tokens (map decode-b64-token
                    (re-seq #"Token[0-9]{1}.+\"(.+)\""
                            (get-in (client/get (str "https://" ip "/motion_data.asp?" dlink-magic-rand)
                                                {:basic-auth [username password]
                                                 :insecure? true}) [:body])))]
    (println
     (client/post (str "https://" ip "/cgi/admin/motion.cgi")
                  {:headers {"TOKEN" (str dlink-magic-rand "@" (apply cal-token tokens))}
                   :insecure? true
                   :basic-auth [username password]
                   :form-params form-params
                   }))))
  
