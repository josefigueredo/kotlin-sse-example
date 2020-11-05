# kotlin-sse-example

## Test

In terminal #1 write:

```bash
curl "http://localhost:8080/stream"\
  -H "Accept: text/event-stream"
```

In terminal #2 write:

```bash
curl -X POST "http://localhost:8080/tweet" \
  -H "Content-type: application/json" \
  -H "Accept: application/json" \
  -d '{  
         "uuid": "'$(uuid -v 4)'",  
         "text": "A tweet about dogs & cats" 
      }'
```

The output of terminal #1 will be:

```bash
data:{"uuid":"d2e99528-f1d2-4007-849c-abcd79ab030c","text":"A tweet about dogs & cats","at":1604540901.490757000}
```

The output of terminal #2 will be:
```bash
1
```

Indicating the number of clients that received the message
