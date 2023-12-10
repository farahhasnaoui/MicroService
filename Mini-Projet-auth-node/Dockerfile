From node:18

WORKDIR /app


COPY  package*.json ./


RUN npm install 


COPY . .

EXPOSE 8880

CMD ["node","server.js"]