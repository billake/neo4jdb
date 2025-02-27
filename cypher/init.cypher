CREATE (d:Domain {name: ""})
CREATE (t:Topic {name: ""})
CREATE (th:Theme {name: ""})
CREATE (q:Question {name: "", type: "", difficulty: "", weight: 0})
CREATE (fq:FollowupQuestion {name: "", type: "", difficulty: "", weight: 0})

CREATE (d)-[:HAS_TOPIC]->(t)
CREATE (t)-[:HAS_THEME]->(th)
CREATE (th)-[:HAS_QUESTION]->(q)
CREATE (q)-[:HAS_FOLLOWUP]->(fq);
