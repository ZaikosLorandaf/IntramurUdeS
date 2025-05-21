echo "Demarrage dockers"

cd ./docker/
docker compose up -d

echo "Demarrage termine"

read -n1 -s touche

