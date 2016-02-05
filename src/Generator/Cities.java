package Generator;

import java.util.Random;

/**
 * Created by tomasz on 05.02.16.
 */
public class Cities {

    public static  String[] cities =
            {"Mount Clemens           " ,"Port Heiden             ","Salineville             "
            ,"Independent Hill        " ,"Isabela                 ","Maunawili               "
            ,"Angier                  " ,"Gibson                  ","Blanco                  "
            ,"Largo                   " ,"Cal-Nev-Ari             ","Emery                   "
            ,"Kooskia                 " ,"Homeland Park           ","Dillwyn                 "
            ,"Glenshaw                " ,"Union Center            ","Buttonwillow            "
            ,"Alvordton               " ,"Janesville              ","Lagunitas Forest Knolls "
            ,"Fordsville              " ,"Armour                  ","Doney Park              "
            ,"Indian Shores           " ,"Tom Bean                ","Ualapu'e                "
            ,"New Troy                " ,"Chalkyitsik             ","Washington Terrace      "
            ,"Cement                  " ,"Dalmatia                ","Lacey                   "
            ,"Hartley                 " ,"Mantoloking             ","Kenansville             "
            ,"LaCrosse                " ,"Fox Run                 ","Twin City               "
            ,"South Beach             " ,"Riverbank               ","Orion                   "
            ,"Bountiful               " ,"Union Beach             ","Hager City              "
            ,"Deering                 " ,"Lakes of the North      ","Valley City             "
            ,"Sulphur Rock            " ,"La Follette             ","Walden                  "
            ,"Summerton               " ,"Randlett                ","Minor Hill              "
            ,"Nara Visa               " ,"Madaket                 ","Constableville          "
            ,"Naranja                 " ,"Broad Brook             ","Chesterton              "
            ,"Ravine                  " ,"Wales                   ","San Augustine           "
            ,"Kerrville               " ,"Old Brownsboro Place    ","West Chester            "
            ,"Layton                  " ,"Funk                    ","Olive Branch            "
            ,"Greenwood Lake          " ,"Old Field               ","Holcombe                "
            ,"North Miami             " ,"Laureldale              ","Rodey                   "
            ,"Stilwell                " ,"Delaware Water Gap      ","Valmy                   "
            ,"Galva                   " ,"Frostproof              ","Baxter                  "
            ,"Coral Springs           " ,"West Brookfield         ","Universal               "
            ,"McFarland               " ,"Bowling Green           ","Seven Mile Ford         "
            ,"Big Beaver              " ,"Escondida               ","New England             "
            ,"Soquel                  " ,"Brices Creek            ","Rio Rancho              "
            ,"West Hempstead          " ,"Carmet                  ","Mobeetie                "
            ,"Asheville               " ,"Strong                  ","Bluewell                "
            ,"Gerster                 " ,"Wilmot                  ","Slaughter Beach         "
            ,"Vidette                 " ,"Palisade                ","Sayville                "
            ,"Trout Creek             " ,"Beaver Crossing         ","Free Soil               "
            ,"Mattydale               " ,"Pajaro Dunes            ","Clayton                 "
            ,"Rio Canas Abajo         " ,"Cooksville              ","Vermilion               "
            ,"Binford                 " ,"Reevesville             ","Calistoga               "
            ,"Mount Hood Village      " ,"Grandview               ","Rhinebeck               "
            ,"Lake Quivira            " ,"Woodall                 ","Iglesia Antigua         "
            ,"Huber Ridge             " ,"Russell Springs         ","Stewartville            "
            ,"Arrey                   " ,"Pennwyn                 ","Plattville              "
            ,"Powhattan               " ,"Three Springs           ","Holyrood                "
            ,"Tindall                 " ,"Creston                 ","Coto Laurel             "
            ,"Shorewood               " ,"Bay Head                ","Valmeyer                "
            ,"Loch Lloyd              " ,"Nimmons                 ","Kahului                 "
            ,"San Pasqual             " ,"Frankfort Square        ","Santaquin               "
            ,"South Salt Lake         " ,"Kawela Bay              ","McCool                  "
            ,"Luverne                 " ,"Elbridge                ","Rye Brook               "
            ,"Cammack Village         " ,"Shell                   ","East Nicolaus           "
            ,"Birney                  " ,"Laona                   ","Johnson Siding          "
            ,"Big Thicket Lake Estates" ,"Sylvanite               ","Ellsinore               "
            ,"Huetter                 " ,"Northwood               ","Lebo                    "
            ,"Susank                  " ,"Heritage Creek          ","Pearsall                "
            ,"Hardesty                " ,"Crescent Valley         ","De Witt                 "
            ,"Stratton                " ,"Sattley                 ","Blevins                 "
            ,"Silesia                 " ,"Websters Crossing       ","Gulfport                "
            ,"Boyd                    " ,"Tagg Flats              ","Bayou Cane              "
            ,"Bucksport               " ,"Cumberland Center       ","Rockbridge              "
            ,"Frystown                " ,"Leando                  ","West Pocomoke           "
            ,"Valley City             " ,"Nisswa                  ","Palco                   "
            ,"West Slope              " ,"Crownpoint              ","Pistakee Highlands      "
            ,"Barranquitas            " ,"Stanberry               ","Hartsdale               "
            ,"Long Branch             " ,"Washington Boro         ","Tidioute                "
            ,"Polk City               " ,"Mount Victory           ","Sawmills                "
            ,"Fort Knox               " ,"Braham                  ","Cienegas Terrace        "
            ,"Schlusser               " ,"Santa Fe Springs        ","Newcastle               "
            ,"La Carla                " ,"Sunrise Beach Village   ","Yorktown                "
            ,"Ualapu'e                " ,"Drummond                ","Anadarko                "
            ,"Novelty Hill            " ,"Aquebogue               ","Istachatta              "
            ,"Avenal                  " ,"Ticonderoga             ","Cloquet                 "
            ,"Deemston                " ,"Earlington              ","Lahaina                 "
            ,"Cousins Island          " ,"Bonner Springs          ","Princeton               "
            ,"Parkston                " ,"Mohall                  ","Viborg                  "
            ,"Vado                    " ,"Disney                  ","Palm Beach Gardens      "
            ,"Marlinton               " ,"Chesterton              ","Campti                  "
            ,"Casselman               " ,"Seneca Gardens          ","New Hampshire           "
            ,"Fort Payne              " ,"Mermentau               ","Timbercreek Canyon      "
            ,"Unadilla                " ,"Duanesburg              ","White Deer              "
            ,"Mitchell Heights        " ,"Glenmoor                ","East Troy               "
            ,"Lake Almanor            " ,"Marcellus               ","Pine Village            "
            ,"West Yarmouth           " ,"Port Matilda            ","Madera                  "
            ,"Bajandas                " ,"Mineral Ridge           ","Swartz                  "
            ,"Warson Woods            " ,"Reidsville              ","Scotts Valley           "
            ,"Acushnet Center         " ,"Ponshewaing             ","Morehead                "
            ,"Cinco Bayou             " ,"Castle Pines North      ","Carmi                   "
            ,"Thaxton                 " ,"Grayridge               ","Cochran                 "
            ,"Duquesne                " ,"Dudley                  ","Sawyerville             "
            ,"Lake Odessa             " ,"Roxobel                 ","Maple Hill              "
            ,"Brooksburg              " ,"Ringsted                ","Cape Royale             "
            ,"South Greenfield        " ,"Ohiowa                  ","Womens Bay              "
            ,"Pompano Beach           " ,"West Chazy              ","Irrigon                 "
            ,"Artemus                 " ,"Knob Noster             ","Port Allen              "
            ,"Garden Grove            " ,"Red Bluff               ","Bluford                 "
            ,"Diamond Ridge           " ,"Edisto Beach            ","Riviera                 "
            ,"Bryn Athyn              " ,"Ellis Grove             ","Glouster                "
            ,"Waverly City            " ,"Polo                    ","Branford Center         "
            ,"West University Place   " ,"St. Pierre              ","Reynolds Heights        "
            ,"Roopville               " ,"South Riding            ","Diablock                "};

    public static String getCity()
    {
        Random random = new Random();
        int val = random.nextInt(cities.length);
        return cities[val].trim();
    }
}