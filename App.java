public class App {
    private int total = 0;
    // calcule le prix payé par l'utilisateur dans le restaurant, en fonction de type de
    // repas qu'il prend (assiette ou sandwich), de la taille de la boisson (petit, moyen, grand), du dessert et
    // de son type (normal ou special) et si il prend un café ou pas (yes ou no).
    // les prix sont fixes pour chaque type de chose mais des réductions peuvent s'appliquer
    // si cela rentre dans une formule!
    public int Compute(String type, String name, String beverage, String size, String dessert, String dsize, String coffee) {

        if (type == null || name == null || type.isEmpty() || name.isEmpty()) return -1;

        Types mapped_type= Types.valueOf(type);
        Size mapped_size = Size.valueOf(size);
        DsizeType mapped_dsize = DsizeType.valueOf(dsize);

        switch (mapped_type){
            case assiette: total += 15; break;
            case sandwich: total += 10; break;
        }

        if (mapped_type == Types.assiette) {
            if (mapped_size == Size.petit) {
                compute_small_size(DsizeType.valueOf(dsize));
            } else if (mapped_size == Size.moyen) {
                compute_size(3, DsizeType.valueOf(dsize), 18, 4, false);
          } else if (mapped_size == Size.grand) {
                compute_size(4, DsizeType.valueOf(dsize), 2, 21, true);
            }
        }
        // mode sandwich
        else if (mapped_type == Types.sandwich){
            if (mapped_size == Size.petit) {
                compute_small_size(DsizeType.valueOf(dsize));
              } else if (mapped_size == Size.moyen) {
                compute_size(3, DsizeType.valueOf(dsize), 13, 4, true);
            } else if (mapped_size == Size.grand) {
                compute_size(4, DsizeType.valueOf(dsize), 2, 4, true);
            }
        }
        if (mapped_type == Types.assiette && mapped_size == Size.moyen && mapped_dsize == DsizeType.normal && coffee.equals("yes")) {
            System.out.print(" avec café offert!");
        } else if (!coffee.equals("yes")){
            total += 1;
        }
        return total;
    }

    private void compute_small_size(DsizeType dsize) {
        total += 2;
        if (dsize == DsizeType.normal) {
                total += 2;
        } else {
                total += 4;
        }
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