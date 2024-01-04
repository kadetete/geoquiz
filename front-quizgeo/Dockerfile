FROM node:20.9.0 as build
WORKDIR /frontend
COPY package.json package-lock.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . .
EXPOSE 80