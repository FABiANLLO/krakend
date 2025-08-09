#!/bin/bash

# Inicializar variables
# Timeout en segundos
d=""
n=""
s=""
t=10

# Procesar las opciones usando getopts
while getopts "d:n:s:t:" opt; do
  case $opt in
  d) d=$OPTARG ;;
  n) n=$OPTARG ;;
  s) s=$OPTARG ;;
  t) t=$OPTARG ;;
  *)
    echo "Opción no válida: -$OPTARG" >&2
    exit 1
    ;;
  esac
done

echo "Esperando para revisar los pods..."
sleep $t
#!/bin/bash
echo "Revisión de los pods en deployment $d y namespace $n..."
# Nombre del Deployment
DEPLOYMENT_NAME=$d
# Espacio de nombres (namespace), si no está en el default
NAMESPACE=$n

# Obtener los nombres de los pods asociados al deployment
PODS=$($KUBECTL_COMMAND get pods -n $NAMESPACE -l app=$DEPLOYMENT_NAME -o jsonpath="{.items[*].metadata.name}")

# Recorrer cada pod y ejecutar el comando dentro de cada contenedor
for POD in $PODS; do
  echo "Get del pod: $POD"
  $KUBECTL_COMMAND get pod -n $NAMESPACE $POD
  echo "Describe del pod: $POD"
  $KUBECTL_COMMAND describe pod -n $NAMESPACE $POD
  echo "Logs del pod: $POD"
  $KUBECTL_COMMAND logs -n $NAMESPACE $POD
done

SERVICE_NAME=$s

echo "Revisión del servicio $s..."
# Obtener el describe del servicio
$KUBECTL_COMMAND describe service $SERVICE_NAME -n $NAMESPACE
