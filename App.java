public class App {
    private int total = 0;
    // calcule le prix payé par l'utilisateur dans le restaurant, en fonction de type de
    // repas qu'il prend (assiette ou sandwich), de la taille de la boisson (petit, moyen, grand), du dessert et
    // de son type (normal ou special) et si il prend un café ou pas (yes ou no).
    // les prix sont fixes pour chaque type de chose mais des réductions peuvent s'appliquer
    // si cela rentre dans une formule!
    public int Compute(String type, String name, String beverage, String size, String dessert, String dsize, String coffee) {
        // prix total à payer pour le client

        // le type ne peut être vide car le client doit déclarer au moins un repas
        if (type == null || name == null || type.isEmpty() || name.isEmpty()) return -1;

        Types mapped_type= Types.valueOf(type);
        Size mapped_size = Size.valueOf(size);
        DsizeType mapped_dsize = DsizeType.valueOf(dsize);

        if (mapped_type == Types.assiette) {
            total += 15;
            // ainsi qu'une Poisson de taille:
            if (mapped_size == Size.petit) {
                compute_size(2, DsizeType.valueOf(dsize), 2, 4, true);
                // si on prends moyen
            } else if (size=="moyen") {
                compute_size(3, DsizeType.valueOf(dsize), 18, 4, false);
          } else if (mapped_size == Size.grand) {
                compute_size(4, DsizeType.valueOf(dsize), 2, 21, true);
            }
        }
        // mode sandwich
        else if (mapped_type == Types.sandwich){
            total += 10;
            // ainsi qu'une boisson de taille:
            if (mapped_size == Size.petit) {
                compute_size(2, DsizeType.valueOf(dsize), 2, 4, true);
                // si on prends moyen
              } else if (mapped_size == Size.moyen) {
                compute_size(3, DsizeType.valueOf(dsize), 13, 4, true);
            } else if (mapped_size == Size.grand) {
                compute_size(4, DsizeType.valueOf(dsize), 2, 4, true);
            }
        }
        if (mapped_type == Types.assiette && mapped_size == Size.moyen && mapped_dsize == DsizeType.normal && coffee.equals("yes")) {
            System.out.print(" avec café offert!");
        } else if (!coffee.equals("yes")){
            // Assume coffee costs 1 unit, adding to the total only if coffee is not included
            total += 1;
        }
        return total;
    }


    public void compute_size(int add_total, DsizeType dsize, int total_normal, int total_other, boolean is_added){
        total += add_total;
        if (dsize == DsizeType.normal) {
            System.out.print("Prix Formule Standard appliquée ");
            if (is_added)
                total += total_normal;
            else
                total = total_normal;
        } else {
            // dans ce cas on a la fomule max
            System.out.print("Prix Formule Max appliquée ");
            if (is_added)
                total = total_other;
            else
                total += total_other;
        }
    }
}