package com.ecograd.ecograd.bootstrap;

import com.ecograd.ecograd.model.Point;
import com.ecograd.ecograd.model.Region;
import com.ecograd.ecograd.model.Role;
import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.repository.PointRepository;
import com.ecograd.ecograd.repository.RegionRepository;
import com.ecograd.ecograd.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DataHolder {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RegionRepository regionRepository;
    private final PointRepository pointRepository;

    public DataHolder(UserRepository userRepository, PasswordEncoder passwordEncoder, RegionRepository regionRepository , PointRepository pointRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.regionRepository = regionRepository;
        this.pointRepository = pointRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User("kostova", passwordEncoder.encode("tamara123"), "Tamara", "Kostova", "kostovatamara@gmail.com", LocalDate.now(), 0d, Role.USER);
        userRepository.save(user);

        List<Double> poly = Arrays.asList(
                42.00557, 21.41743,
                42.00688, 21.40609,
                41.99651, 21.40388,
                41.99347, 21.42483,
                41.99809, 21.42550,
                42.00026, 21.42739
        );
        List<Point> points = new ArrayList<>();
        for (int i=0;i<poly.size();i+=2){
            Point p = new Point(poly.get(i), poly.get(i+1));
            pointRepository.save(p);
            points.add(p);
        }
        if (regionRepository.count()==0) {
            regionRepository.save(new Region(points, 0.0, "Karposh 1 i 2, Debar Maalo"));
            poly = Arrays.asList(
                    42.00698, 21.40605,
                    42.01005, 21.38216,
                    42.00116, 21.37737,
                    41.99645, 21.40343
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Karposh 3 i 4, Taftalidze 1 i 2"));
            poly = Arrays.asList(
                    41.99354, 21.46232,
                    41.99432, 21.46551,
                    41.99093, 21.47412,
                    41.98782, 21.48717,
                    41.97945, 21.47870,
                    41.98987, 21.45757
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Aerodrom 2(Jane)"));
            poly = Arrays.asList(
                    41.99347, 21.42483,
                    41.99809, 21.42550,
                    42.00026, 21.42739,
                    41.99905, 21.42961,
                    41.99882, 21.44055,
                    41.99408, 21.44085,
                    41.98964, 21.43313
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Centar hexagon"));
            poly = Arrays.asList(
                    41.98256, 21.47188,
                    41.97710, 21.46461,
                    41.98898, 21.43911,
                    41.98776, 21.43680,
                    41.98938, 21.43311,
                    41.99404, 21.44108,
                    41.99459, 21.44931,
                    41.98978, 21.45806
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Aerodrom 1"));
            poly = Arrays.asList(
                    41.98207, 21.47121,
                    41.97502, 21.46290,
                    41.96378, 21.48827,
                    41.97636, 21.50137,
                    41.98253, 21.49680,
                    41.98734, 21.48818,
                    41.97900, 21.47872
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Lisice"));
            poly = Arrays.asList(
                    42.00134, 21.37741,
                    41.99734, 21.37021,
                    42.00116, 21.36319,
                    42.00698, 21.36771,
                    42.00854, 21.36852,
                    42.01263, 21.38314
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Vlae i Hrom"));
            poly = Arrays.asList(
                    42.00863, 21.36798,
                    42.00137, 21.36339,
                    41.99882, 21.35499,
                    42.00322, 21.34178,
                    42.00705, 21.33216,
                    42.01029, 21.33134,
                    42.01085, 21.35566,
                    42.01070, 21.36254
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Gjorce"));
            poly = Arrays.asList(
                    41.99317, 21.42441,
                    42.00101, 21.37702,
                    41.99701, 21.37071,
                    41.98634, 21.40373,
                    41.98795, 21.41778
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Kozle,Kapistec"));
            poly = Arrays.asList(
                    41.97713, 21.46439,
                    41.97192, 21.46006,
                    41.96874, 21.44893,
                    41.97558, 21.42883,
                    41.98263, 21.41701,
                    41.9819, 21.4074,
                    41.98811, 21.41763,
                    41.99329, 21.42459,
                    41.98950, 21.43321,
                    41.98776, 21.43683,
                    41.98907, 21.43924
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Kisela Voda"));
            poly = Arrays.asList(
                    42.00534, 21.44096,
                    42.03998, 21.45506,
                    42.04292, 21.44581,
                    42.02638, 21.42920,
                    42.01500, 21.41340
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "butel"));
            poly = Arrays.asList(
                    42.00524, 21.44184,
                    42.02443, 21.45110,
                    42.00348, 21.47424,
                    41.99886, 21.45084,
                    42.00524, 21.44146
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "zelezara i avtokomanda"));
            poly = Arrays.asList(
                    41.99912, 21.43003,
                    42.00537, 21.41724,
                    42.00791, 21.39130,
                    42.01017, 21.38346,
                    42.01251, 21.38625,
                    42.00950, 21.39500,
                    42.01246, 21.40343,
                    42.01115, 21.41187,
                    42.01311, 21.41710,
                    42.00822, 21.42377,
                    42.00579, 21.42862
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }

            regionRepository.save(new Region(points, 0.0, "Vlada,Park"));
            poly = Arrays.asList(
                    41.99647, 21.44999,
                    41.99829, 21.44455,
                    41.99871, 21.43994,
                    41.99921, 21.43208,
                    42.00724, 21.42904,
                    42.01219, 21.42406,
                    42.00876, 21.43327,
                    42.00504, 21.44083,
                    41.99890, 21.45073
            );
            points = new ArrayList<>();
            for (int i = 0; i < poly.size(); i += 2) {
                Point p = new Point(poly.get(i), poly.get(i + 1));
                pointRepository.save(p);
                points.add(p);
            }
            regionRepository.save(new Region(points, 0.0, "Charshija"));
        }
    }
}
