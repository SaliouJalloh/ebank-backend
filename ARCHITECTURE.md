## 🚦 Règles d’architecture

✅ Les contrôleurs utilisent uniquement les **dtos**
✅ Les services utilisent uniquement les **models** pour leur interface et logique publique
✅ Les repositories manipulent uniquement les **entities** (`*Entity`)
✅ Les conversions `Entity` ↔ `Model` passent **toujours par un `*Mapper`**
✅ Tous les services/persistence ont une **interface `I*`**
✅ Les classes concrètes n’ont **pas de suffixe Impl**
✅ L’injection se fait uniquement par **constructeur**
