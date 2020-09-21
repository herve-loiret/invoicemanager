
## Docker

### build
To create a docker image of this project, run: 

```bash
docker build -t invoicemanager .
docker run -p 8080:8080 -t invoicemanager
```