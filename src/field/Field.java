package field;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import core.Game;
import core.STATS;

public final class Field {

    public static final int FIELD_W = (int) (STATS.WINDOW_W * 0.75);
    public static final int FIELD_H = STATS.WINDOW_H;

    //solte am besten duch FIELD_W und FIELD_H teilbar sein
    static final int TILES_HOR = STATS.TILES_HOR, TILES_VER = STATS.TILES_VER;

    private Tile[][] tiles = createTiles();
    private List<Dynamite> dynamite = new ArrayList<>();
    private List<Explosion> booms = new ArrayList<>();
    private Game game;

    private final BufferedImage background = createBg();

    public Field(Game g) {
        game = g;
    }

    private BufferedImage createBg() {
        BufferedImage out = null;
        try {
            out = ImageIO.read(new File("res/rustytile.jpg"));
            //out = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    private Tile[][] createTiles() {
        Tile[][] out = new Tile[Field.TILES_HOR][Field.TILES_VER];
        for (int x = 1; x < Field.TILES_HOR - 1; x++) {
            for (int y = 1; y < Field.TILES_VER - 1; y++) {
                out[x][y] = new BreakTile(x, y);
            }
        }
        out[1][1] = new EmptyTile(1, 1);
        out[1][2] = new EmptyTile(1, 2);
        out[2][1] = new EmptyTile(2, 1);

        out[Field.TILES_HOR - 2][Field.TILES_VER - 2] = new EmptyTile(Field.TILES_HOR - 2, Field.TILES_VER - 2);
        out[Field.TILES_HOR - 3][Field.TILES_VER - 2] = new EmptyTile(Field.TILES_HOR - 3, Field.TILES_VER - 2);
        out[Field.TILES_HOR - 2][Field.TILES_VER - 3] = new EmptyTile(Field.TILES_HOR - 2, Field.TILES_VER - 3);

        for (int i = 0; i < Field.TILES_VER; i++) {
            out[i][0] = new SolidTile(i, 0);
            out[i][Field.TILES_VER - 1] = new SolidTile(i, Field.TILES_VER - 1);
        }
        for (int i = 0; i < Field.TILES_HOR; i++) {
            out[0][i] = new SolidTile(0, i);
            out[Field.TILES_HOR - 1][i] = new SolidTile(Field.TILES_HOR - 1, i);
        }
        for (int y = 2; y < Field.TILES_VER; y += 2) {
            for (int x = 2; x < Field.TILES_HOR; x += 2) {
                out[x][y] = new SolidTile(x, y);
            }
        }
        return out;
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, FIELD_W, FIELD_H, null);
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                tiles[x][y].draw(g);
            }
        }
        for (int i = 0; i < dynamite.size(); i++) {
            dynamite.get(i).draw(g);
        }
        for (int i = 0; i < booms.size(); i++) {
            booms.get(i).draw(g);
            ;
        }
    }


    public void update(double fd) {
        for (int i = 0; i < dynamite.size(); i++) {
            dynamite.get(i).update(fd);
        }
        for (int i = 0; i < booms.size(); i++) {
            booms.get(i).update(fd);
        }
    }


    public boolean intersects(Rectangle obj) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y].getBounding().intersects(obj) && tiles[x][y].isSolid()) {
                    return true;
                }
            }
        }
        return false;

    }

    public void addDynamite(float x, float y) {
        dynamite.add(new Dynamite(x, y, this));
    }

    public void explodeDynamite(Dynamite obj) {
        //intersection with other blocks and their destruction
        Explosion EXP = new Explosion(obj.getX(), obj.getY(), this);
        dynamite.remove(obj);
        booms.add(EXP);
        Rectangle[] hit = EXP.getHitbox();
        for (int e = 0; e < hit.length; e++) {
            for (int x = 0; x < tiles.length; x++) {
                for (int y = 0; y < tiles[0].length; y++) {
                    if (tiles[x][y].isSolid() && tiles[x][y].isBreakable()
                            && hit[e].intersects(tiles[x][y].getBounding())) {
                        tiles[x][y] = new EmptyTile(x, y);
                        Rectangle[] ex = EXP.getDrawmodel();
                        if (e == 0) {
                            ex[0].setBounds(ex[0].x, ex[0].y - Tile.TILE_H, ex[0].width, ex[0].height + Tile.TILE_H);
                        } else if (e == 1) {
                            ex[1].setBounds(ex[1].x, ex[1].y, ex[1].width + Tile.TILE_W, ex[1].height);
                        } else if (e == 2) {
                            ex[2].setBounds(ex[2].x, ex[2].y, ex[2].width, ex[2].height + Tile.TILE_H);
                        } else if (e == 3) {
                            ex[3].setBounds(ex[3].x - Tile.TILE_W, ex[3].y, ex[3].width + Tile.TILE_W, ex[3].height);
                        }
                        break;
                    }
                }
            }
        }
        //player intersection:
        for (int e = 0; e < hit.length; e++) {
            int f = game.player_intsect(hit[e]);
            if (f != -1) {
                game.kill(f);
            }
        }


    }

    public void done(Explosion obj) {
        booms.remove(obj);
    }
}
