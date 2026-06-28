#Instalando uma imagem Node dentro do container
FROM node:18

#Criando o diretório da aplicação
WORKDIR /app

#Copiando os arquivos package.json do projeto
COPY package*.json ./

#Instalando as bibliotecas do projeto
RUN npm install

#Copiando os demais arquivo do projeto
COPY . .

#Expoe a porta para rodar a aplicação
EXPOSE 3000

#Executando a aplicação
CMD ["node", "index.js"]