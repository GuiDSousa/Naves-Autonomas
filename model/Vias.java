package model;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por representar as vias no sistema de tráfeo autônomo.
public class Vias {
    private List<Rua> ruas;

    public Vias() {
        this.ruas = new ArrayList<>();
        inicializarRuas();
    }

    private void inicializarRuas() {
        // Ruas horizontais
        // As ruas horizontais estão nomeadas com 3 caracteres e um número que é o equivalente ao indice do array em que foi adcionado.
        // Avenida Horizontal 1
        adicionarRua("ELM_0", 100, 560, 200, 560);
        adicionarRua("OAK_1", 200, 560, 296, 560);
        adicionarRua("ANA_2", 296, 560, 393, 560);
        adicionarRua("ASH_3", 393, 560, 490, 560);
        adicionarRua("BEE_4", 490, 560, 586, 560);
        // Avenida Horizontal 2
        adicionarRua("SKY_5", 100, 460, 200, 460);
        adicionarRua("IVY_6", 200, 460, 296, 460);
        adicionarRua("SUN_7", 296, 460, 393, 460);   
        adicionarRua("DEW_8", 393, 460, 490, 460);
        adicionarRua("FOG_9", 490, 460, 586, 460);
        // Avenida Horizontal 3
        adicionarRua("ZEN_10", 100, 360, 200, 360);
        adicionarRua("JET_11", 200, 360, 296, 360);
        adicionarRua("JOY_12", 296, 360, 393, 360);
        adicionarRua("LUX_13", 393, 360, 490, 360);  
        adicionarRua("ORB_14", 490, 360, 586, 360);
        // Avenida Horizontal 4
        adicionarRua("PIP_15", 100, 260, 200, 260);
        adicionarRua("PET_16", 200, 260, 296, 260);
        adicionarRua("INK_17", 296, 260, 393, 260);
        adicionarRua("POD_18", 393, 260, 490, 260);
        adicionarRua("HEX_19", 490, 260, 586, 260);
        // Avenida Horizontal 5
        adicionarRua("JAX_20", 100, 160, 200, 160);
        adicionarRua("KAY_21", 200, 160, 296, 160);
        adicionarRua("ACE_22", 296, 160, 393, 160);
        adicionarRua("FIN_23", 393, 160, 490, 160);
        adicionarRua("DOC_24", 490, 160, 586, 160);
        // Avenida Horizontal 6
        adicionarRua("WIN_25", 100, 60, 200, 60);
        adicionarRua("BUD_26", 200, 60, 296, 60);
        adicionarRua("AXE_27", 296, 60, 393, 60);
        adicionarRua("ZOE_28", 393, 60, 490, 60);
        adicionarRua("GAL_29", 490, 60, 586, 60);

        // Ruas verticais
        // As ruas verticais são nomeadas com 4 caracteres e um número que é o equivalente ao indice do array em que foi adcionado.
        // Avenida Vertical 1
        adicionarRua("AIKO_30", 100, 560, 100, 460);
        adicionarRua("ZORO_31", 100, 460, 100, 360);
        adicionarRua("VINE_32", 100, 360, 100, 260);
        adicionarRua("ROSE_33", 100, 260, 100, 160);
        adicionarRua("HALO_34", 100, 160, 100, 60);
        // Avenida Vertical 2
        adicionarRua("AIMI_35", 200, 560, 200, 460);
        adicionarRua("FUJI_36", 200, 460, 200, 360);
        adicionarRua("AQUA_37", 200, 360, 200, 260);
        adicionarRua("IRIS_38", 200, 260, 200, 160);
        adicionarRua("NOVA_39", 200, 160, 200, 60);
        // Avenida Vertical 3
        adicionarRua("CHOU_40", 296, 560, 296, 460);
        adicionarRua("EREN_41", 296, 460, 296, 360);
        adicionarRua("GAZE_42", 296, 360, 296, 260);
        adicionarRua("LUSH_43", 296, 260, 296, 160);
        adicionarRua("DUSK_44", 296, 160, 296, 60);
        // Avenida Vertical 4
        adicionarRua("NEJI_45", 393, 560, 393, 460);
        adicionarRua("YOKA_46", 393, 460, 393, 360);
        adicionarRua("HILL_47", 393, 360, 393, 260);
        adicionarRua("LUNA_48", 393, 260, 393, 160);
        adicionarRua("VIBE_49", 393, 160, 393, 60);
        // Avenida Vertical 5
        adicionarRua("PAIN_50", 490, 560, 490, 460);
        adicionarRua("HANA_51", 490, 460, 490, 360);
        adicionarRua("SILK_52", 490, 360, 490, 260);
        adicionarRua("ECHO_53", 490, 260, 490, 160);
        adicionarRua("ZEST_54", 490, 160, 490, 60);
        // Avenida Vertical 6
        adicionarRua("BUGG_55", 586, 560, 586, 460);
        adicionarRua("HIRO_56", 586, 460, 586, 360);
        adicionarRua("PAVE_57", 586, 360, 586, 260);
        adicionarRua("MIST_58", 586, 260, 586, 160);
        adicionarRua("ZENO_59", 586, 160, 586, 60);
    }

    private void adicionarRua(String nome, int startX, int startY, int endX, int endY) {
        Rua rua = new Rua(nome, startX, startY, endX, endY);
        ruas.add(rua);
    }

    private void adicionarPercursoAhsoka() {
        // O percurso da Ahsoka
        adicionarRua("BEE_4", 490, 560, 586, 560);
        adicionarRua("ASH_3", 393, 560, 490, 560);
        adicionarRua("ANA_2", 296, 560, 393, 560);
        adicionarRua("OAK_1", 200, 560, 296, 560);
        adicionarRua("ELM_0", 100, 560, 200, 560);

        adicionarRua("AIKO_30", 100, 460, 100, 560);
        adicionarRua("ZORO_31", 100, 360, 100, 460);
        adicionarRua("VINE_32", 100, 260, 100, 360);
        adicionarRua("ROSE_33", 100, 160, 100, 260);
        adicionarRua("HALO_34", 100, 60, 100, 160);

        adicionarRua("WIN_25", 100, 60, 200, 60);
        adicionarRua("BUD_26", 200, 60, 296, 60);

        adicionarRua("DUSK_44", 296, 160, 296, 60);
        adicionarRua("LUSH_43", 296, 260, 296, 160);
        adicionarRua("GAZE_42", 296, 360, 296, 260);

        adicionarRua("JOY_12", 296, 360, 393, 360);
        adicionarRua("LUX_13", 393, 360, 490, 360);
        adicionarRua("ORB_14", 490, 360, 586, 360);

        adicionarRua("HIRO_56", 586, 160, 586, 60);
        adicionarRua("BUGG_55", 586, 160, 586, 260);
    }

    public class Rua {
        private String nome;
        private int startX;
        private int startY;
        private int endX;
        private int endY;


        public Rua (String nome, int startX, int startY, int endX, int endY) {
        this.nome = nome;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public String getNome() {
        return nome;
    } // Fim do método getNome

    public int getStartX() {
        return startX;
    } // Fim do método getStartX

    public int getStartY() {
        return startY;
    } // Fim do método getStartY

    public int getEndX() {
        return endX;
    } // Fim do método getEndX

    public int getEndY() {
        return endY;
    } // Fim do método getEndY
    
    } // Fim da classe Rua

    public void imprimirArrayRuas() {
        for (Rua rua : ruas) {
            // Imprimir nome da rua e indice do array
            System.out.println(rua.getNome());
            // Imprimir coordenadas de inicio e fim da rua

        }
    } // Fim do método imprimirArrayRuas



} // Fim da classe Vias
