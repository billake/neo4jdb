######  RUN:
/~ sbt

/~ run

###### API:
* import json (using HTTPie)

```bash

http POST localhost:8080/api/import < src/main/resources/json/import.json 
http GET localhost:8080/api/export

```

###### JSON Example (src/main/resources/json/import.json): 
```json
{
  "domains": [
    {
      "title": "Frontend",
      "topics": [
        {
          "title": "React",
          "themes": [
            {
              "title": "Virtual DOM",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "What is the Virtual DOM and how does it work?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "What is the difference between the Virtual DOM and the Real DOM?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "How does React's diffing algorithm work to update the Real DOM?"
                        }
                      ]
                    },
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "Why is the Virtual DOM considered more efficient for rendering?"
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "Explain the reconciliation process in React.",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What are keys in React and why are they important for reconciliation?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Component Lifecycle",
              "questions": [
                {
                  "weight": 6,
                  "tags": ["junior", "middle"],
                  "title": "Explain the component lifecycle methods in React.",
                  "followUpQuestions": [
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "How do lifecycle methods differ between class components and functional components with hooks?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "When would you use componentDidUpdate or useEffect with dependencies?"
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "What are React Hooks and why were they introduced?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "Explain the rules of Hooks and why they're important."
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "How would you implement a custom Hook and in what scenarios would you use one?"
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          "title": "JavaScript",
          "themes": [
            {
              "title": "Asynchronous JavaScript",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "Explain Promises and their benefits over callbacks.",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "What is Promise chaining and how does it work?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "How do you handle errors in Promise chains?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "Explain the difference between Promise.all, Promise.race, Promise.allSettled, and Promise.any."
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "What is async/await and how does it simplify asynchronous code?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["middle", "senior"],
                      "title": "How do you handle errors with async/await?"
                    },
                    {
                      "weight": 7,
                      "tags": ["junior", "middle"],
                      "title": "Can you convert a function that returns a Promise to use async/await? Provide an example."
                    }
                  ]
                }
              ]
            },
            {
              "title": "Closures and Scope",
              "questions": [
                {
                  "weight": 6,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "What is a closure in JavaScript?",
                  "followUpQuestions": [
                    {
                      "weight": 7,
                      "tags": ["middle"],
                      "title": "How can closures be used to create private variables?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "What are potential memory leak issues with closures and how can they be avoided?"
                    }
                  ]
                },
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "Explain lexical scope in JavaScript.",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "What is the difference between function scope and block scope?"
                    },
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "How do let, const, and var differ in terms of scoping?"
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    },
    {
      "title": "Backend",
      "topics": [
        {
          "title": "Node.js",
          "themes": [
            {
              "title": "Event Loop",
              "questions": [
                {
                  "weight": 8,
                  "tags": ["middle", "senior", "mike wasowski interview"],
                  "title": "What is the Node.js Event Loop and how does it work?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What is the difference between the Event Loop and the Call Stack?",
                      "followUpQuestions": [
                        {
                          "weight": 10,
                          "tags": ["senior"],
                          "title": "How do microtasks and macrotasks get processed in the Event Loop?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "How does the Event Loop handle I/O operations differently from synchronous code?"
                    }
                  ]
                },
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "What are Node.js streams and how do they relate to the Event Loop?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you handle backpressure in Node.js streams?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Performance Optimization",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "What techniques can you use to improve the performance of a Node.js application?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How does the Node.js cluster module help with performance?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "What are the trade-offs of using the cluster module versus a load balancer?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How can you identify and fix memory leaks in Node.js applications?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "Explain how to implement caching strategies in Node.js applications.",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "When would you use in-memory caching versus distributed caching?"
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          "title": "Databases",
          "themes": [
            {
              "title": "SQL vs NoSQL",
              "questions": [
                {
                  "weight": 6,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "What are the key differences between SQL and NoSQL databases?",
                  "followUpQuestions": [
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "In what scenarios would you choose a SQL database over a NoSQL database?",
                      "followUpQuestions": [
                        {
                          "weight": 8,
                          "tags": ["senior"],
                          "title": "How would you handle relational data in a NoSQL database?"
                        }
                      ]
                    },
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "Explain the CAP theorem and how it applies to different database types."
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["senior"],
                  "title": "How do you design a database schema for scalability?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What strategies would you use for database sharding and partitioning?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you handle database migrations with zero downtime?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Database Optimization",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "What techniques would you use to optimize database queries?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you identify and fix slow queries in a production environment?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "What tools would you use to monitor database performance?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "Explain database indexing and how it impacts query performance."
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "How do you implement transaction management in your applications?",
                  "followUpQuestions": [
                    {
                      "weight": 10,
                      "tags": ["senior"],
                      "title": "What are the ACID properties and how do they relate to database transactions?"
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    },
    {
      "title": "DevOps",
      "topics": [
        {
          "title": "Docker",
          "themes": [
            {
              "title": "Containers",
              "questions": [
                {
                  "weight": 6,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "What is a container and how does it differ from a virtual machine?",
                  "followUpQuestions": [
                    {
                      "weight": 7,
                      "tags": ["middle"],
                      "title": "What are the advantages of using containers for deployment?",
                      "followUpQuestions": [
                        {
                          "weight": 8,
                          "tags": ["senior"],
                          "title": "How do namespaces and cgroups enable container isolation in Linux?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you ensure security in containerized environments?"
                    }
                  ]
                },
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "Explain the concept of Docker layers and how they impact image size and build time.",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "What best practices would you follow when writing a Dockerfile?"
                    },
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "How would you optimize a Docker image for size and security?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Docker Compose",
              "questions": [
                {
                  "weight": 6,
                  "tags": ["junior", "middle"],
                  "title": "What is Docker Compose and how does it simplify multi-container applications?",
                  "followUpQuestions": [
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "How do you manage environment variables and secrets in Docker Compose?",
                      "followUpQuestions": [
                        {
                          "weight": 8,
                          "tags": ["senior"],
                          "title": "What are the best practices for handling secrets in a containerized environment?"
                        }
                      ]
                    },
                    {
                      "weight": 7,
                      "tags": ["middle"],
                      "title": "How do you handle container networking in Docker Compose?"
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "How would you use Docker Compose for local development versus production environments?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What strategies would you use to ensure parity between development and production environments?"
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          "title": "CI/CD",
          "themes": [
            {
              "title": "Continuous Integration",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["junior", "middle"],
                  "title": "What is Continuous Integration and what are its benefits?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "How would you set up a CI pipeline for a modern web application?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "What are some strategies to handle flaky tests in a CI pipeline?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "How do you ensure code quality in a CI pipeline?"
                    }
                  ]
                },
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "How do you implement automated testing in a CI pipeline?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What testing strategies work best in a CI environment?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you balance test coverage with build time in a CI pipeline?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Continuous Deployment",
              "questions": [
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "What is Continuous Deployment and how does it differ from Continuous Delivery?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What are the risks of Continuous Deployment and how do you mitigate them?",
                      "followUpQuestions": [
                        {
                          "weight": 10,
                          "tags": ["senior"],
                          "title": "How do you implement feature flags in a CD environment?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How would you handle database migrations in a Continuous Deployment pipeline?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "Explain different deployment strategies (blue-green, canary, rolling) and their trade-offs.",
                  "followUpQuestions": [
                    {
                      "weight": 10,
                      "tags": ["senior"],
                      "title": "How would you implement a canary deployment strategy?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you handle rollbacks in different deployment strategies?"
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    },
    {
      "title": "System Design",
      "topics": [
        {
          "title": "Microservices",
          "themes": [
            {
              "title": "Service Communication",
              "questions": [
                {
                  "weight": 8,
                  "tags": ["middle", "senior"],
                  "title": "What are the different patterns for communication between microservices?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "When would you use synchronous versus asynchronous communication?",
                      "followUpQuestions": [
                        {
                          "weight": 10,
                          "tags": ["senior"],
                          "title": "How would you handle failures in synchronous and asynchronous communication models?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "What are the advantages and disadvantages of using an API Gateway?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "How would you implement service discovery in a microservices architecture?",
                  "followUpQuestions": [
                    {
                      "weight": 10,
                      "tags": ["senior"],
                      "title": "What are the challenges of service discovery and how do modern tools address them?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Data Management",
              "questions": [
                {
                  "weight": 8,
                  "tags": ["senior"],
                  "title": "How do you handle data consistency across microservices?",
                  "followUpQuestions": [
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What is the Saga pattern and when would you use it?",
                      "followUpQuestions": [
                        {
                          "weight": 10,
                          "tags": ["senior"],
                          "title": "How do you implement compensating transactions in a distributed system?"
                        }
                      ]
                    },
                    {
                      "weight": 9,
                      "tags": ["senior"],
                      "title": "What is eventual consistency and how do you design systems around it?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "What patterns would you use for querying data across multiple services?",
                  "followUpQuestions": [
                    {
                      "weight": 10,
                      "tags": ["senior"],
                      "title": "How would you implement CQRS in a microservices architecture?"
                    },
                    {
                      "weight": 8,
                      "tags": ["senior", "mike wasowski interview"],
                      "title": "What are the trade-offs of using a GraphQL API versus multiple REST endpoints?"
                    }
                  ]
                }
              ]
            }
          ]
        },
        {
          "title": "Scalability",
          "themes": [
            {
              "title": "Horizontal vs Vertical Scaling",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["junior", "middle", "mike wasowski interview"],
                  "title": "What is the difference between horizontal and vertical scaling?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "When would you choose horizontal scaling over vertical scaling?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "What are the challenges of horizontally scaling stateful services?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["middle", "senior"],
                      "title": "How do modern cloud platforms support different scaling approaches?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["senior"],
                  "title": "How would you design a system to handle a sudden 10x increase in traffic?",
                  "followUpQuestions": [
                    {
                      "weight": 10,
                      "tags": ["senior"],
                      "title": "What auto-scaling strategies would you implement for unpredictable traffic patterns?"
                    }
                  ]
                }
              ]
            },
            {
              "title": "Caching Strategies",
              "questions": [
                {
                  "weight": 7,
                  "tags": ["middle", "senior"],
                  "title": "What are different caching strategies and when would you use each?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How do you handle cache invalidation in a distributed system?",
                      "followUpQuestions": [
                        {
                          "weight": 9,
                          "tags": ["senior"],
                          "title": "What are the trade-offs between different cache eviction policies?"
                        }
                      ]
                    },
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "How would you implement a multi-level caching strategy?"
                    }
                  ]
                },
                {
                  "weight": 9,
                  "tags": ["middle", "senior", "mike wasowski interview"],
                  "title": "How do CDNs work and when should you use them?",
                  "followUpQuestions": [
                    {
                      "weight": 8,
                      "tags": ["senior"],
                      "title": "What are edge computing capabilities in modern CDNs and how can they be leveraged?"
                    },
                    {
                      "weight": 7,
                      "tags": ["middle", "senior"],
                      "title": "How do you handle dynamic content with CDNs?"
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
}

```
