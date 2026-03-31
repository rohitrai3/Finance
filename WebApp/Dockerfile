FROM node:24-alpine

WORKDIR /app

COPY . .

RUN npm install
RUN npm i -g serve
RUN npm run build
RUN chmod +x /app/start.sh


EXPOSE 3000

CMD ["sh", "/app/start.sh"]
