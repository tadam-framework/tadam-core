(ns tadam.sessions)

(defn add-session
  "Add session in request"
  [req key value]
  (assoc-in req [:session key] value))

(defn get-key-session
  "Get value from key session"
  [req key]
  (-> req :session key))

(defn session?
  "Check if exist session"
  [req key]
  (not (nil? (get-key-session req key))))

(defn delete-key-session
  "Delete session from request"
  [req key]
  (update-in req [:session] dissoc key))

(defn delete-sessions
  "Delete all sessions from request"
  [req]
  (assoc req :session nil))
