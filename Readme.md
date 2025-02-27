######  RUN:
/~ sbt

/~ run

###### FOR TEST:
```bash
curl -X POST http://localhost:8080/import \
-H "Content-Type: application/json" \
-d '{
  "domains": [
    {
      "name": "Backend Development",
      "topics": [
        {
          "name": "Node.js",
          "themes": [
            {
              "name": "Event Loop",
              "questions": [
                {
                  "name": "Can you explain how the event loop works in Node.js?",
                  "type": "binary",
                  "difficulty": "hard",
                  "weight": 8,
                  "followupQuestions": [
                    {
                      "name": "What are the phases of the event loop in Node.js?",
                      "type": "binary",
                      "difficulty": "hard",
                      "weight": 7
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    }
  ]
}'
