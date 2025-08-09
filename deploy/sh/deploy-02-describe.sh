#!/bin/bash
echo "Esperando para revisar los pods..."
sleep 10
#!/bin/bash
echo "Revisión de los pods..."

# Espacio de nombres (namespace), si no está en el default
NAMESPACE="$APP_KUBE_NAMESPACE"

# Obtener los nombres de los pods asociados al deployment
PODS=$($KUBECTL_COMMAND get pods -n $NAMESPACE -o jsonpath="{.items[*].metadata.name}")

# Recorrer cada pod y ejecutar el comando dentro de cada contenedor
for POD in $PODS; do
  echo "Describe y logs de: $POD"
  $KUBECTL_COMMAND describe pod -n $NAMESPACE $POD
  $KUBECTL_COMMAND logs -n $NAMESPACE $POD
done

$KUBECTL_COMMAND get svc -n $NAMESPACE