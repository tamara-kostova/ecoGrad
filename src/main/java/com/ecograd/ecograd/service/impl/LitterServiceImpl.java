package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.*;
import com.ecograd.ecograd.model.exception.InvalidLitterException;
import com.ecograd.ecograd.repository.LitterRepository;
import com.ecograd.ecograd.repository.RegionRepository;
import com.ecograd.ecograd.service.LitterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LitterServiceImpl implements LitterService {
    private final LitterRepository litterRepository;
    private final RegionRepository regionRepository;

    public LitterServiceImpl(LitterRepository litterRepository, RegionRepository regionRepository) {
        this.litterRepository = litterRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Litter> findAll() {
        return litterRepository.findAll();
    }

    @Override
    public Litter addLitter(Litter litter) {
        Region region = null;
        Point point = new Point(litter.getLatitude(), litter.getLongitude());
        List<Region> regions = regionRepository.findAll();
        for (Region value : regions) {
            List<Point> polygon = value.getPoints();
            if (checkInside(polygon, polygon.size(), point) == 1) {
                region = value;
                break;
            }
        }
        litter.setRegion(region);
        if(litter.getRegion()!=null){
            region = litter.getRegion();
            region.setScore(region.getScore()+litter.getScore());
            regionRepository.save(region);
        }
        return litterRepository.save(litter);
    }

    @Override
    public Litter findById(Long id) {
        return litterRepository.findById(id).orElseThrow(InvalidLitterException::new);
    }

    @Override
    public List<Litter> findByUserUsername(String username) {
        return litterRepository.findAll().stream().filter(litter -> litter.getUser().getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public List<Litter> findAllByRegion(Region r) {
        return litterRepository.findAllByRegion(r);
    }

    public static class Line {
        public Point p1, p2;
        public Line(Point p1, Point p2)
        {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    static int onLine(Line l1, Point p)
    {
        // Check whether p is on the line or not
        if (p.getX() <= Math.max(l1.p1.getX(), l1.p2.getX())
                && p.getX() >= Math.min(l1.p1.getX(), l1.p2.getX())
                && (p.getY() <= Math.max(l1.p1.getY(), l1.p2.getY())
                && p.getY() >= Math.min(l1.p1.getY(), l1.p2.getY())))
            return 1;

        return 0;
    }

    static int direction(Point a, Point b, Point c)
    {
        double val = (b.getY() - a.getY()) * (c.getX() - b.getX())
                - (b.getX() - a.getX()) * (c.getY() - b.getY());

        if (val == 0)

            // Collinear
            return 0;

        else if (val < 0)

            // Anti-clockwise direction
            return 2;

        // Clockwise direction
        return 1;
    }

    static int isIntersect(Line l1, Line l2)
    {
        // Four direction for two lines and points of other
        // line
        int dir1 = direction(l1.p1, l1.p2, l2.p1);
        int dir2 = direction(l1.p1, l1.p2, l2.p2);
        int dir3 = direction(l2.p1, l2.p2, l1.p1);
        int dir4 = direction(l2.p1, l2.p2, l1.p2);

        // When intersecting
        if (dir1 != dir2 && dir3 != dir4)
            return 1;

        // When p2 of line2 are on the line1
        if (dir1 == 0 && onLine(l1, l2.p1) == 1)
            return 1;

        // When p1 of line2 are on the line1
        if (dir2 == 0 && onLine(l1, l2.p2) == 1)
            return 1;

        // When p2 of line1 are on the line2
        if (dir3 == 0 && onLine(l2, l1.p1) == 1)
            return 1;

        // When p1 of line1 are on the line2
        if (dir4 == 0 && onLine(l2, l1.p2) == 1)
            return 1;

        return 0;
    }

    static int checkInside(List<Point> poly, int n, Point point)
    {

        // When polygon has less than 3 edge, it is not
        // polygon

        if (n < 3)
            return 0;

        // Create a point at infinity, y is same as point p
        Point pt = new Point(9999.0, point.getY());
        Line exline = new Line(point, pt);
        int count = 0;
        int i = 0;
        do {

            // Forming a line from two consecutive points of
            // poly
            Line side
                    = new Line(poly.get(i), poly.get((i + 1) % n));
            if (isIntersect(side, exline) == 1) {

                // If side is intersects exline
                if (direction(side.p1, point, side.p2) == 0)
                    return onLine(side, point);
                count++;
            }
            i = (i + 1) % n;
        } while (i != 0);

        // When count is odd
        return count & 1;
    }




}
