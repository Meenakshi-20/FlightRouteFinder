// ============================================================
//  DataLoader.java
//  All airports and flights in one place.
//  Domestic (India) + International routes.
//  Add more flights here anytime!
// ============================================================
public class DataLoader {

    public static void load(FlightGraph g) {

        // =====================================================
        //  DOMESTIC AIRPORTS (India)
        // =====================================================
        Airport DEL = new Airport("DEL", "New Delhi",   "India", "DOMESTIC");
        Airport BOM = new Airport("BOM", "Mumbai",      "India", "DOMESTIC");
        Airport HYD = new Airport("HYD", "Hyderabad",   "India", "DOMESTIC");
        Airport MAA = new Airport("MAA", "Chennai",     "India", "DOMESTIC");
        Airport BLR = new Airport("BLR", "Bangalore",   "India", "DOMESTIC");
        Airport CCU = new Airport("CCU", "Kolkata",     "India", "DOMESTIC");
        Airport AMD = new Airport("AMD", "Ahmedabad",   "India", "DOMESTIC");
        Airport COK = new Airport("COK", "Kochi",       "India", "DOMESTIC");
        Airport PNQ = new Airport("PNQ", "Pune",        "India", "DOMESTIC");
        Airport JAI = new Airport("JAI", "Jaipur",      "India", "DOMESTIC");
        Airport GOI = new Airport("GOI", "Goa",         "India", "DOMESTIC");
        Airport LKO = new Airport("LKO", "Lucknow",     "India", "DOMESTIC");
        Airport ATQ = new Airport("ATQ", "Amritsar",    "India", "DOMESTIC");
        Airport IXC = new Airport("IXC", "Chandigarh",  "India", "DOMESTIC");
        Airport BHO = new Airport("BHO", "Bhopal",      "India", "DOMESTIC");
        Airport NAG = new Airport("NAG", "Nagpur",      "India", "DOMESTIC");

        // =====================================================
        //  INTERNATIONAL AIRPORTS
        // =====================================================
        Airport DXB = new Airport("DXB", "Dubai",           "UAE",          "INTERNATIONAL");
        Airport SIN = new Airport("SIN", "Singapore",       "Singapore",    "INTERNATIONAL");
        Airport LHR = new Airport("LHR", "London Heathrow", "UK",           "INTERNATIONAL");
        Airport JFK = new Airport("JFK", "New York JFK",    "USA",          "INTERNATIONAL");
        Airport BKK = new Airport("BKK", "Bangkok",         "Thailand",     "INTERNATIONAL");
        Airport KUL = new Airport("KUL", "Kuala Lumpur",    "Malaysia",     "INTERNATIONAL");
        Airport CDG = new Airport("CDG", "Paris CDG",       "France",       "INTERNATIONAL");
        Airport FRA = new Airport("FRA", "Frankfurt",       "Germany",      "INTERNATIONAL");
        Airport SYD = new Airport("SYD", "Sydney",          "Australia",    "INTERNATIONAL");
        Airport NRT = new Airport("NRT", "Tokyo Narita",    "Japan",        "INTERNATIONAL");
        Airport DOH = new Airport("DOH", "Doha",            "Qatar",        "INTERNATIONAL");
        Airport AUH = new Airport("AUH", "Abu Dhabi",       "UAE",          "INTERNATIONAL");
        Airport HKG = new Airport("HKG", "Hong Kong",       "Hong Kong",    "INTERNATIONAL");
        Airport ICN = new Airport("ICN", "Seoul Incheon",   "South Korea",  "INTERNATIONAL");

        // Add every airport to the graph
        Airport[] all = {
            DEL, BOM, HYD, MAA, BLR, CCU, AMD, COK, PNQ, JAI, GOI, LKO, ATQ, IXC, BHO, NAG,
            DXB, SIN, LHR, JFK, BKK, KUL, CDG, FRA, SYD, NRT, DOH, AUH, HKG, ICN
        };
        for (Airport a : all) g.addAirport(a);

        // =====================================================
        //  DOMESTIC FLIGHTS
        //  Format: flightNumber, airline, from, to,
        //          depart, arrive, durationMins, priceINR,
        //          class, seats, type
        // =====================================================

        // --- Delhi <-> Mumbai ---
        g.addFlight(new Flight("AI-101",  "Air India",  DEL, BOM, "06:00","08:05", 125, 4500,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("AI-102",  "Air India",  BOM, DEL, "09:00","11:05", 125, 4500,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-201",  "IndiGo",     DEL, BOM, "07:30","09:35", 125, 3800,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-202",  "IndiGo",     BOM, DEL, "10:30","12:35", 125, 3800,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("SG-301",  "SpiceJet",   DEL, BOM, "14:00","16:10", 130, 3200,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("SG-302",  "SpiceJet",   BOM, DEL, "17:00","19:10", 130, 3200,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("UK-101",  "Vistara",    DEL, BOM, "08:00","10:05", 125, 7500,  "Business",  40, "DOMESTIC"));
        g.addFlight(new Flight("UK-102",  "Vistara",    BOM, DEL, "11:30","13:35", 125, 7500,  "Business",  40, "DOMESTIC"));

        // --- Delhi <-> Hyderabad ---
        g.addFlight(new Flight("AI-111",  "Air India",  DEL, HYD, "07:00","09:20", 140, 4800,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("AI-112",  "Air India",  HYD, DEL, "10:00","12:20", 140, 4800,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-211",  "IndiGo",     DEL, HYD, "08:30","10:50", 140, 4100,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-212",  "IndiGo",     HYD, DEL, "11:30","13:50", 140, 4100,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("SG-311",  "SpiceJet",   DEL, HYD, "13:00","15:25", 145, 3600,  "Economy",  110, "DOMESTIC"));
        g.addFlight(new Flight("SG-312",  "SpiceJet",   HYD, DEL, "16:00","18:25", 145, 3600,  "Economy",  110, "DOMESTIC"));

        // --- Delhi <-> Bangalore ---
        g.addFlight(new Flight("AI-121",  "Air India",  DEL, BLR, "06:30","09:00", 150, 5200,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("AI-122",  "Air India",  BLR, DEL, "10:00","12:30", 150, 5200,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("UK-121",  "Vistara",    DEL, BLR, "09:00","11:35", 155, 8500,  "Business",  50, "DOMESTIC"));
        g.addFlight(new Flight("UK-122",  "Vistara",    BLR, DEL, "12:30","15:05", 155, 8500,  "Business",  50, "DOMESTIC"));
        g.addFlight(new Flight("6E-221",  "IndiGo",     DEL, BLR, "07:00","09:35", 155, 4600,  "Economy",  170, "DOMESTIC"));
        g.addFlight(new Flight("6E-222",  "IndiGo",     BLR, DEL, "10:30","13:05", 155, 4600,  "Economy",  170, "DOMESTIC"));

        // --- Delhi <-> Chennai ---
        g.addFlight(new Flight("AI-131",  "Air India",  DEL, MAA, "06:15","09:00", 165, 5500,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("AI-132",  "Air India",  MAA, DEL, "10:00","12:45", 165, 5500,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-231",  "IndiGo",     DEL, MAA, "07:30","10:15", 165, 4700,  "Economy",  175, "DOMESTIC"));
        g.addFlight(new Flight("6E-232",  "IndiGo",     MAA, DEL, "11:00","13:45", 165, 4700,  "Economy",  175, "DOMESTIC"));

        // --- Delhi <-> Kolkata ---
        g.addFlight(new Flight("6E-241",  "IndiGo",     DEL, CCU, "07:00","09:30", 150, 4200,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-242",  "IndiGo",     CCU, DEL, "10:30","13:00", 150, 4200,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("AI-141",  "Air India",  DEL, CCU, "08:00","10:35", 155, 4600,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("AI-142",  "Air India",  CCU, DEL, "11:30","14:05", 155, 4600,  "Economy",  140, "DOMESTIC"));

        // --- Delhi <-> Ahmedabad ---
        g.addFlight(new Flight("AI-151",  "Air India",  DEL, AMD, "07:30","09:15", 105, 3500,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("AI-152",  "Air India",  AMD, DEL, "10:30","12:15", 105, 3500,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("6E-251",  "IndiGo",     DEL, AMD, "09:00","10:50", 110, 2900,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-252",  "IndiGo",     AMD, DEL, "12:00","13:50", 110, 2900,  "Economy",  160, "DOMESTIC"));

        // --- Delhi <-> Jaipur ---
        g.addFlight(new Flight("6E-261",  "IndiGo",     DEL, JAI, "06:00","07:10",  70, 1800,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-262",  "IndiGo",     JAI, DEL, "08:00","09:10",  70, 1800,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("SG-361",  "SpiceJet",   DEL, JAI, "14:00","15:15",  75, 1500,  "Economy",  100, "DOMESTIC"));
        g.addFlight(new Flight("SG-362",  "SpiceJet",   JAI, DEL, "16:30","17:45",  75, 1500,  "Economy",  100, "DOMESTIC"));

        // --- Delhi <-> Lucknow ---
        g.addFlight(new Flight("6E-271",  "IndiGo",     DEL, LKO, "09:00","10:10",  70, 1900,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-272",  "IndiGo",     LKO, DEL, "11:00","12:10",  70, 1900,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("AI-161",  "Air India",  DEL, LKO, "07:00","08:15",  75, 2300,  "Economy",  130, "DOMESTIC"));
        g.addFlight(new Flight("AI-162",  "Air India",  LKO, DEL, "09:30","10:45",  75, 2300,  "Economy",  130, "DOMESTIC"));

        // --- Delhi <-> Amritsar / Chandigarh ---
        g.addFlight(new Flight("6E-281",  "IndiGo",     DEL, ATQ, "07:00","08:00",  60, 1600,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-282",  "IndiGo",     ATQ, DEL, "09:00","10:00",  60, 1600,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-291",  "IndiGo",     DEL, IXC, "06:30","07:20",  50, 1400,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-292",  "IndiGo",     IXC, DEL, "08:30","09:20",  50, 1400,  "Economy",  160, "DOMESTIC"));

        // --- Mumbai <-> South India ---
        g.addFlight(new Flight("AI-201",  "Air India",  BOM, HYD, "07:00","08:25",  85, 3200,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("AI-202",  "Air India",  HYD, BOM, "09:00","10:25",  85, 3200,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-401",  "IndiGo",     BOM, BLR, "06:30","08:00",  90, 2900,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-402",  "IndiGo",     BLR, BOM, "09:00","10:30",  90, 2900,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("SG-401",  "SpiceJet",   BOM, MAA, "08:00","10:05", 125, 3400,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("SG-402",  "SpiceJet",   MAA, BOM, "11:00","13:05", 125, 3400,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("UK-201",  "Vistara",    BOM, COK, "11:00","12:45", 105, 6800,  "Business",  40, "DOMESTIC"));
        g.addFlight(new Flight("UK-202",  "Vistara",    COK, BOM, "14:00","15:45", 105, 6800,  "Business",  40, "DOMESTIC"));
        g.addFlight(new Flight("6E-411",  "IndiGo",     BOM, GOI, "08:30","09:35",  65, 2200,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-412",  "IndiGo",     GOI, BOM, "11:00","12:05",  65, 2200,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-421",  "IndiGo",     BOM, PNQ, "07:00","07:50",  50, 1500,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-422",  "IndiGo",     PNQ, BOM, "09:00","09:50",  50, 1500,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("AI-211",  "Air India",  BOM, LKO, "08:00","10:20", 140, 4300,  "Economy",  130, "DOMESTIC"));
        g.addFlight(new Flight("AI-212",  "Air India",  LKO, BOM, "11:30","13:50", 140, 4300,  "Economy",  130, "DOMESTIC"));
        g.addFlight(new Flight("AI-221",  "Air India",  BOM, NAG, "09:00","10:20",  80, 2800,  "Economy",  120, "DOMESTIC"));
        g.addFlight(new Flight("AI-222",  "Air India",  NAG, BOM, "11:30","12:50",  80, 2800,  "Economy",  120, "DOMESTIC"));

        // --- South India inter-city ---
        g.addFlight(new Flight("AI-301",  "Air India",  BLR, HYD, "08:00","09:10",  70, 2800,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("AI-302",  "Air India",  HYD, BLR, "10:00","11:10",  70, 2800,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-501",  "IndiGo",     BLR, MAA, "07:00","08:00",  60, 2500,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-502",  "IndiGo",     MAA, BLR, "09:00","10:00",  60, 2500,  "Economy",  180, "DOMESTIC"));
        g.addFlight(new Flight("6E-511",  "IndiGo",     BLR, COK, "09:30","10:30",  60, 2400,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("6E-512",  "IndiGo",     COK, BLR, "11:30","12:30",  60, 2400,  "Economy",  150, "DOMESTIC"));
        g.addFlight(new Flight("AI-311",  "Air India",  MAA, HYD, "07:30","08:40",  70, 2700,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("AI-312",  "Air India",  HYD, MAA, "09:30","10:40",  70, 2700,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("SG-501",  "SpiceJet",   MAA, COK, "08:30","09:30",  60, 2300,  "Economy",  110, "DOMESTIC"));
        g.addFlight(new Flight("SG-502",  "SpiceJet",   COK, MAA, "11:00","12:00",  60, 2300,  "Economy",  110, "DOMESTIC"));
        g.addFlight(new Flight("6E-521",  "IndiGo",     HYD, COK, "10:00","11:30",  90, 3100,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-522",  "IndiGo",     COK, HYD, "12:30","14:00",  90, 3100,  "Economy",  160, "DOMESTIC"));

        // --- Kolkata routes ---
        g.addFlight(new Flight("AI-401",  "Air India",  CCU, BLR, "07:00","09:30", 150, 4900,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("AI-402",  "Air India",  BLR, CCU, "10:30","13:00", 150, 4900,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("6E-601",  "IndiGo",     CCU, HYD, "08:00","10:10", 130, 4400,  "Economy",  170, "DOMESTIC"));
        g.addFlight(new Flight("6E-602",  "IndiGo",     HYD, CCU, "11:00","13:10", 130, 4400,  "Economy",  170, "DOMESTIC"));
        g.addFlight(new Flight("6E-611",  "IndiGo",     CCU, MAA, "07:30","10:00", 150, 4600,  "Economy",  160, "DOMESTIC"));
        g.addFlight(new Flight("6E-612",  "IndiGo",     MAA, CCU, "11:00","13:30", 150, 4600,  "Economy",  160, "DOMESTIC"));

        // --- Nagpur / Bhopal ---
        g.addFlight(new Flight("AI-501",  "Air India",  NAG, DEL, "08:00","10:00", 120, 3800,  "Economy",  100, "DOMESTIC"));
        g.addFlight(new Flight("AI-502",  "Air India",  DEL, NAG, "11:00","13:00", 120, 3800,  "Economy",  100, "DOMESTIC"));
        g.addFlight(new Flight("6E-701",  "IndiGo",     BHO, DEL, "06:00","07:45", 105, 2600,  "Economy",  130, "DOMESTIC"));
        g.addFlight(new Flight("6E-702",  "IndiGo",     DEL, BHO, "09:00","10:45", 105, 2600,  "Economy",  130, "DOMESTIC"));
        g.addFlight(new Flight("6E-711",  "IndiGo",     BHO, BOM, "08:00","09:50", 110, 3100,  "Economy",  140, "DOMESTIC"));
        g.addFlight(new Flight("6E-712",  "IndiGo",     BOM, BHO, "11:00","12:50", 110, 3100,  "Economy",  140, "DOMESTIC"));

        // =====================================================
        //  INTERNATIONAL FLIGHTS
        // =====================================================

        // --- India -> Dubai (DXB) ---
        g.addFlight(new Flight("EK-501",  "Emirates",         DEL, DXB, "03:00","05:10", 190, 22000, "Economy",  220, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-502",  "Emirates",         DXB, DEL, "08:00","12:40", 220, 22000, "Economy",  220, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-511",  "Emirates",         BOM, DXB, "04:00","06:00", 180, 20000, "Economy",  220, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-512",  "Emirates",         DXB, BOM, "09:00","13:30", 210, 20000, "Economy",  220, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-601",  "Air India",        HYD, DXB, "02:30","04:45", 195, 19500, "Economy",  160, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-602",  "Air India",        DXB, HYD, "07:30","12:45", 225, 19500, "Economy",  160, "INTERNATIONAL"));
        g.addFlight(new Flight("FZ-501",  "flydubai",         BLR, DXB, "03:15","05:40", 205, 18000, "Economy",  150, "INTERNATIONAL"));
        g.addFlight(new Flight("FZ-502",  "flydubai",         DXB, BLR, "10:00","14:45", 225, 18000, "Economy",  150, "INTERNATIONAL"));
        g.addFlight(new Flight("G9-501",  "Air Arabia",       COK, DXB, "04:30","06:20", 170, 16500, "Economy",  180, "INTERNATIONAL"));
        g.addFlight(new Flight("G9-502",  "Air Arabia",       DXB, COK, "09:30","13:30", 180, 16500, "Economy",  180, "INTERNATIONAL"));

        // --- India -> Doha (DOH) / Qatar ---
        g.addFlight(new Flight("QR-601",  "Qatar Airways",    DEL, DOH, "02:00","03:45", 225, 20000, "Economy",  200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-602",  "Qatar Airways",    DOH, DEL, "09:00","14:00", 240, 20000, "Economy",  200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-611",  "Qatar Airways",    BOM, DOH, "03:00","04:45", 225, 19000, "Economy",  180, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-612",  "Qatar Airways",    DOH, BOM, "06:00","11:00", 240, 19000, "Economy",  180, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-621",  "Qatar Airways",    HYD, DOH, "02:00","03:50", 230, 18500, "Economy",  160, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-622",  "Qatar Airways",    DOH, HYD, "08:00","13:00", 240, 18500, "Economy",  160, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-631",  "Qatar Airways",    MAA, DOH, "03:00","05:00", 240, 19500, "Economy",  170, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-632",  "Qatar Airways",    DOH, MAA, "08:00","13:30", 240, 19500, "Economy",  170, "INTERNATIONAL"));

        // --- India -> Abu Dhabi (AUH) ---
        g.addFlight(new Flight("EY-501",  "Etihad",           DEL, AUH, "04:00","06:00", 180, 21000, "Economy",  200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-502",  "Etihad",           AUH, DEL, "09:00","13:30", 210, 21000, "Economy",  200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-511",  "Etihad",           BOM, AUH, "02:30","04:15", 165, 19000, "Economy",  190, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-512",  "Etihad",           AUH, BOM, "07:00","11:15", 195, 19000, "Economy",  190, "INTERNATIONAL"));

        // --- India -> Singapore (SIN) ---
        g.addFlight(new Flight("SQ-601",  "Singapore Airlines", DEL, SIN, "06:30","14:30", 270, 28000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-602",  "Singapore Airlines", SIN, DEL, "16:00","19:30", 210, 28000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-611",  "Singapore Airlines", BOM, SIN, "07:00","14:40", 280, 26000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-612",  "Singapore Airlines", SIN, BOM, "16:30","19:30", 240, 26000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("6E-801",  "IndiGo",             MAA, SIN, "05:00","11:30", 270, 22000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("6E-802",  "IndiGo",             SIN, MAA, "13:00","15:30", 240, 22000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-621",  "Singapore Airlines", CCU, SIN, "07:30","14:00", 270, 24000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-622",  "Singapore Airlines", SIN, CCU, "15:00","17:00", 240, 24000, "Economy", 180, "INTERNATIONAL"));

        // --- India -> Kuala Lumpur (KUL) ---
        g.addFlight(new Flight("MH-801",  "Malaysia Airlines", BOM, KUL, "07:00","14:55", 295, 25000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("MH-802",  "Malaysia Airlines", KUL, BOM, "17:00","20:30", 240, 25000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("AK-801",  "AirAsia",           BLR, KUL, "06:00","12:45", 285, 19000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AK-802",  "AirAsia",           KUL, BLR, "14:00","16:30", 240, 19000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AK-811",  "AirAsia",           MAA, KUL, "05:30","11:45", 255, 18000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("AK-812",  "AirAsia",           KUL, MAA, "13:00","15:30", 240, 18000, "Economy", 170, "INTERNATIONAL"));

        // --- India -> Bangkok (BKK) ---
        g.addFlight(new Flight("TG-701",  "Thai Airways",  DEL, BKK, "09:15","15:30", 255, 24000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-702",  "Thai Airways",  BKK, DEL, "17:00","20:30", 240, 24000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("6E-811",  "IndiGo",        BOM, BKK, "08:30","14:50", 260, 21000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("6E-812",  "IndiGo",        BKK, BOM, "16:30","19:00", 240, 21000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-711",  "Thai Airways",  BLR, BKK, "10:00","16:05", 245, 22000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-712",  "Thai Airways",  BKK, BLR, "18:00","21:30", 240, 22000, "Economy", 170, "INTERNATIONAL"));

        // --- India -> London (LHR) ---
        g.addFlight(new Flight("AI-801",  "Air India",       DEL, LHR, "14:00","18:30", 510, 62000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-802",  "Air India",       LHR, DEL, "21:30","11:00", 480, 62000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("BA-801",  "British Airways", DEL, LHR, "23:55","05:30", 510, 72000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("BA-802",  "British Airways", LHR, DEL, "14:00","04:10", 500, 72000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("BA-811",  "British Airways", BOM, LHR, "01:25","07:10", 525, 68000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("BA-812",  "British Airways", LHR, BOM, "09:45","23:30", 525, 68000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-811",  "Air India",       BOM, LHR, "02:15","07:45", 510, 60000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-812",  "Air India",       LHR, BOM, "13:00","03:15", 495, 60000, "Economy", 160, "INTERNATIONAL"));

        // --- India -> New York (JFK) ---
        g.addFlight(new Flight("AI-901",  "Air India",        DEL, JFK, "02:00","09:30", 930, 85000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-902",  "Air India",        JFK, DEL, "22:00","22:00", 840, 85000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-911",  "Air India",        BOM, JFK, "01:00","10:00", 960, 88000, "Economy", 140, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-912",  "Air India",        JFK, BOM, "20:00","21:30", 870, 88000, "Economy", 140, "INTERNATIONAL"));

        // --- India -> Paris (CDG) ---
        g.addFlight(new Flight("AF-601",  "Air France",  DEL, CDG, "02:30","07:55", 505, 65000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AF-602",  "Air France",  CDG, DEL, "10:30","23:30", 480, 65000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AF-611",  "Air France",  BOM, CDG, "03:00","08:20", 500, 63000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("AF-612",  "Air France",  CDG, BOM, "11:00","23:15", 495, 63000, "Economy", 170, "INTERNATIONAL"));

        // --- India -> Frankfurt (FRA) ---
        g.addFlight(new Flight("LH-601",  "Lufthansa",   DEL, FRA, "03:00","07:30", 480, 64000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("LH-602",  "Lufthansa",   FRA, DEL, "11:00","23:00", 480, 64000, "Economy", 180, "INTERNATIONAL"));

        // --- India -> Sydney (SYD) ---
        g.addFlight(new Flight("AI-1001", "Air India",          DEL, SYD, "03:00","23:30", 1110, 95000, "Economy", 140, "INTERNATIONAL"));
        g.addFlight(new Flight("AI-1002", "Air India",          SYD, DEL, "22:00","05:00",  900, 95000, "Economy", 140, "INTERNATIONAL"));
        g.addFlight(new Flight("QF-601",  "Qantas",             BOM, SYD, "22:00","18:30",  990, 98000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("QF-602",  "Qantas",             SYD, BOM, "11:00","17:00",  840, 98000, "Economy", 160, "INTERNATIONAL"));

        // --- India -> Tokyo (NRT) ---
        g.addFlight(new Flight("NH-601",  "All Nippon Airways", DEL, NRT, "10:00","22:00", 480, 75000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("NH-602",  "All Nippon Airways", NRT, DEL, "01:00","06:00", 450, 75000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("JL-601",  "Japan Airlines",     BOM, NRT, "09:00","21:30", 510, 78000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("JL-602",  "Japan Airlines",     NRT, BOM, "23:00","05:30", 480, 78000, "Economy", 160, "INTERNATIONAL"));

        // --- India -> Hong Kong (HKG) / Seoul (ICN) ---
        g.addFlight(new Flight("CX-601",  "Cathay Pacific", DEL, HKG, "08:00","15:30", 330, 38000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-602",  "Cathay Pacific", HKG, DEL, "17:00","20:00", 300, 38000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-611",  "Cathay Pacific", BOM, HKG, "09:30","16:45", 315, 36000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-612",  "Cathay Pacific", HKG, BOM, "18:00","21:00", 300, 36000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-601",  "Korean Air",     DEL, ICN, "09:00","19:30", 450, 45000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-602",  "Korean Air",     ICN, DEL, "21:00","01:30", 420, 45000, "Economy", 180, "INTERNATIONAL"));

        // =====================================================
        //  HUB -> HUB (International Connections)
        //  Lets you go e.g. India -> Dubai -> London -> New York
        // =====================================================

        // Dubai -> Europe / USA
        g.addFlight(new Flight("EK-201",  "Emirates",    DXB, LHR, "08:30","12:40", 430, 45000, "Economy", 250, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-202",  "Emirates",    LHR, DXB, "14:00","00:15", 430, 45000, "Economy", 250, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-301",  "Emirates",    DXB, CDG, "09:00","13:15", 435, 44000, "Economy", 240, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-302",  "Emirates",    CDG, DXB, "15:00","23:30", 450, 44000, "Economy", 240, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-401",  "Emirates",    DXB, FRA, "09:30","13:30", 420, 43000, "Economy", 230, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-402",  "Emirates",    FRA, DXB, "15:30","23:45", 435, 43000, "Economy", 230, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-101",  "Emirates",    DXB, JFK, "08:00","14:30", 840, 78000, "Economy", 250, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-102",  "Emirates",    JFK, DXB, "22:00","19:00", 780, 78000, "Economy", 250, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-111",  "Emirates",    DXB, SYD, "22:00","18:00", 840, 72000, "Economy", 230, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-112",  "Emirates",    SYD, DXB, "21:00","05:00", 840, 72000, "Economy", 230, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-121",  "Emirates",    DXB, NRT, "09:00","22:00", 600, 62000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-122",  "Emirates",    NRT, DXB, "01:00","06:00", 570, 62000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-131",  "Emirates",    DXB, SIN, "08:00","14:20", 380, 38000, "Economy", 240, "INTERNATIONAL"));
        g.addFlight(new Flight("EK-132",  "Emirates",    SIN, DXB, "23:00","01:00", 360, 38000, "Economy", 240, "INTERNATIONAL"));

        // Qatar -> World
        g.addFlight(new Flight("QR-201",  "Qatar Airways", DOH, LHR, "07:30","12:00", 390, 42000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-202",  "Qatar Airways", LHR, DOH, "14:00","23:00", 390, 42000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-301",  "Qatar Airways", DOH, JFK, "08:00","15:30", 810, 75000, "Economy", 210, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-302",  "Qatar Airways", JFK, DOH, "22:00","18:00", 780, 75000, "Economy", 210, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-401",  "Qatar Airways", DOH, CDG, "08:00","13:00", 360, 40000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-402",  "Qatar Airways", CDG, DOH, "15:00","22:00", 360, 40000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-501",  "Qatar Airways", DOH, SIN, "09:00","22:30", 510, 46000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-502",  "Qatar Airways", SIN, DOH, "00:30","05:30", 480, 46000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-701",  "Qatar Airways", DOH, SYD, "02:00","21:00", 900, 82000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-702",  "Qatar Airways", SYD, DOH, "22:00","05:00", 870, 82000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-801",  "Qatar Airways", DOH, NRT, "10:00","01:30", 570, 60000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("QR-802",  "Qatar Airways", NRT, DOH, "18:00","22:00", 540, 60000, "Economy", 190, "INTERNATIONAL"));

        // Singapore -> Asia-Pacific
        g.addFlight(new Flight("SQ-701",  "Singapore Airlines", SIN, NRT, "08:00","16:20", 380, 42000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-702",  "Singapore Airlines", NRT, SIN, "18:00","23:30", 390, 42000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-801",  "Singapore Airlines", SIN, SYD, "08:30","18:45", 455, 48000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-802",  "Singapore Airlines", SYD, SIN, "21:00","04:30", 450, 48000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-901",  "Singapore Airlines", SIN, BKK, "08:00","09:15",  75, 18000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-902",  "Singapore Airlines", BKK, SIN, "11:00","14:15",  75, 18000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-911",  "Singapore Airlines", SIN, HKG, "08:30","12:30", 240, 28000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-912",  "Singapore Airlines", HKG, SIN, "14:00","18:00", 240, 28000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-921",  "Singapore Airlines", SIN, ICN, "09:00","17:00", 360, 36000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-922",  "Singapore Airlines", ICN, SIN, "19:00","23:30", 330, 36000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-931",  "Singapore Airlines", SIN, LHR, "23:00","05:30", 780, 68000, "Economy", 220, "INTERNATIONAL"));
        g.addFlight(new Flight("SQ-932",  "Singapore Airlines", LHR, SIN, "12:00","08:00", 780, 68000, "Economy", 220, "INTERNATIONAL"));

        // London -> Europe -> USA
        g.addFlight(new Flight("LH-201",  "Lufthansa",    LHR, FRA, "07:00","09:20",  80, 18000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("LH-202",  "Lufthansa",    FRA, LHR, "11:00","11:50",  80, 18000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("AF-201",  "Air France",   LHR, CDG, "07:30","09:50",  80, 17000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("AF-202",  "Air France",   CDG, LHR, "11:00","11:20",  80, 17000, "Economy", 160, "INTERNATIONAL"));
        g.addFlight(new Flight("AA-201",  "American Airlines", JFK, LHR, "19:00","07:10", 430, 52000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("AA-202",  "American Airlines", LHR, JFK, "11:00","14:00", 430, 52000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("AA-211",  "American Airlines", JFK, CDG, "18:30","08:10", 460, 50000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("AA-212",  "American Airlines", CDG, JFK, "11:30","13:30", 420, 50000, "Economy", 200, "INTERNATIONAL"));

        // KUL / BKK -> Pacific
        g.addFlight(new Flight("MH-901",  "Malaysia Airlines", KUL, SYD, "23:00","10:30", 510, 38000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("MH-902",  "Malaysia Airlines", SYD, KUL, "12:00","18:00", 480, 38000, "Economy", 150, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-801",  "Thai Airways",      BKK, SYD, "22:30","10:00", 510, 40000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-802",  "Thai Airways",      SYD, BKK, "11:30","17:00", 510, 40000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-811",  "Thai Airways",      BKK, NRT, "08:00","15:30", 330, 34000, "Economy", 170, "INTERNATIONAL"));
        g.addFlight(new Flight("TG-812",  "Thai Airways",      NRT, BKK, "17:30","22:30", 300, 34000, "Economy", 170, "INTERNATIONAL"));

        // HKG / ICN -> Japan / Pacific
        g.addFlight(new Flight("CX-701",  "Cathay Pacific", HKG, NRT, "09:00","13:30", 270, 30000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-702",  "Cathay Pacific", NRT, HKG, "15:00","18:30", 270, 30000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-711",  "Cathay Pacific", HKG, SYD, "23:00","10:00", 570, 50000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("CX-712",  "Cathay Pacific", SYD, HKG, "11:00","18:30", 510, 50000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-701",  "Korean Air",     ICN, JFK, "11:00","11:00", 840, 72000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-702",  "Korean Air",     JFK, ICN, "01:00","04:00", 810, 72000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-711",  "Korean Air",     ICN, NRT, "07:00","08:30",  90, 18000, "Economy", 180, "INTERNATIONAL"));
        g.addFlight(new Flight("KE-712",  "Korean Air",     NRT, ICN, "10:00","11:30",  90, 18000, "Economy", 180, "INTERNATIONAL"));

        // Frankfurt -> USA / Asia
        g.addFlight(new Flight("LH-301",  "Lufthansa",   FRA, JFK, "11:00","14:00", 540, 58000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("LH-302",  "Lufthansa",   JFK, FRA, "18:00","08:00", 480, 58000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("LH-401",  "Lufthansa",   FRA, SIN, "22:00","15:30", 570, 65000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("LH-402",  "Lufthansa",   SIN, FRA, "01:00","08:00", 690, 65000, "Economy", 190, "INTERNATIONAL"));

        // Etihad (Abu Dhabi) hub
        g.addFlight(new Flight("EY-201",  "Etihad",      AUH, LHR, "09:00","13:00", 420, 43000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-202",  "Etihad",      LHR, AUH, "14:00","00:30", 420, 43000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-301",  "Etihad",      AUH, JFK, "09:00","15:00", 840, 76000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-302",  "Etihad",      JFK, AUH, "22:00","18:00", 780, 76000, "Economy", 200, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-401",  "Etihad",      AUH, SYD, "22:00","18:00", 840, 73000, "Economy", 190, "INTERNATIONAL"));
        g.addFlight(new Flight("EY-402",  "Etihad",      SYD, AUH, "21:00","05:00", 840, 73000, "Economy", 190, "INTERNATIONAL"));
    }
}